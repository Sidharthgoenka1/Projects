package com.capstone.capstoneProject.orderManagement.orderLineItems;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.capstoneProject.ProjectConstants;
import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.RetrofitBuilder;
import com.capstone.capstoneProject.dashboard.model.OrderStatusMaster;
import com.capstone.capstoneProject.orderManagement.api.OrderApi;
import com.capstone.capstoneProject.orderManagement.model.OrderDetailsItemHistoryPojo;
import com.capstone.capstoneProject.orderManagement.model.OrderDetailsItemsHistoryWrapper;
import com.capstone.capstoneProject.orderManagement.model.OrderItemsPojo;
import com.capstone.capstoneProject.orderManagement.model.UpdateItemsPojo;
import com.capstone.capstoneProject.orderManagement.viewOrders.ViewOrderHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderLineItemsAdapter extends RecyclerView.Adapter<OrderLineItemsAdapter.ViewHolder> {

    private Context context;
    private String userId, userType;
    private List<OrderItemsPojo> data;

    public OrderLineItemsAdapter(Context context, String t, String i, List<OrderItemsPojo> d) {
        this.context = context;
        userId = i;
        userType = t;
        data = d;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.order_details_card_view_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderItemsPojo s = data.get(position);
        holder.distName.setText(s.getDistName());
        holder.distId.setText(s.getDistId()+"");
        holder.wName.setText(s.getwName());
        holder.wId.setText(s.getwId()+"");
        holder.invName.setText(ProjectConstants.invIdMasterMap.get(s.getInvId()).getName());
        holder.invId.setText(s.getInvId()+"");
        holder.statusTv.setText(ProjectConstants.orderStatusMasterMap.get(s.getStatusId()).getStatusVal());

        clickedAction(holder, position);
    }

    public void clickedAction(@NonNull ViewHolder holder, int position){

        holder.historyIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderItemsPojo o = data.get(position);
                getItemHistory(o);
            }
        });

        holder.orderDetailsCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrderItemsPojo o = data.get(position);
                if(checkOrderStatus(o)){
                    if (userType.equalsIgnoreCase("d")) {
                        //D can Approve, Cancelled, Shipped, Delivered
                        if(o.getStatusId() == 1 || o.getStatusId() == 3 || o.getStatusId() == 4){
                            openUpdatePopup(holder,o);
                        }else {
                            Toast.makeText(context,context.getString(R.string.order_not_able_to_update), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //S can place, make payment, receive, reject
                        if(o.getStatusId() == 2 || o.getStatusId() == 6 || o.getStatusId() == 4){
                            openUpdatePopup(holder,o);
                        }else {
                            Toast.makeText(context,context.getString(R.string.order_not_able_to_update), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    private void getItemHistory(OrderItemsPojo o) {
        try{
            RetrofitBuilder.showProgressDialog(context);
        }catch (Exception e){}
        OrderApi api = RetrofitBuilder.createPostLoginRetrofit().create(OrderApi.class);
        Call<OrderDetailsItemsHistoryWrapper> call = api.getItemsHistory(o.getInvId() + "", o.getOrderId() + "");
        call.enqueue(new Callback<OrderDetailsItemsHistoryWrapper>() {
            @Override
            public void onResponse(Call<OrderDetailsItemsHistoryWrapper> call, Response<OrderDetailsItemsHistoryWrapper> response) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(response.body() != null && response.body().getStatusCode() == 200){
                    if(response.body().getMessage() != null && response.body().getMessage().size()>0){
                            openHistoryPopup(response.body().getMessage(), o.getOrderId()+"");
                    }else {
                        Toast.makeText(context, context.getString(R.string.no_history_found), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context, context.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderDetailsItemsHistoryWrapper> call, Throwable t) {
                try{
                    RetrofitBuilder.dismissProgressDialog();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(context, context.getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openHistoryPopup(List<OrderDetailsItemHistoryPojo> d, String orderId) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.show_order_history_dialog_layout);
        dialog.setCancelable(false);

        RecyclerView historyRv = dialog.findViewById(R.id.order_history_rv);
        ImageView cancelIv = dialog.findViewById(R.id.cancel_iv);
        TextView headingTv = dialog.findViewById(R.id.heading_tv);

        headingTv.setText(context.getString(R.string.history_for)+" "+context.getString(R.string.order_id)+" :- "+orderId);
        cancelIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        historyRv.setLayoutManager(new LinearLayoutManager(context));
        historyRv.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));

        ViewOrderHistoryAdapter adapter = new ViewOrderHistoryAdapter(d, context);
        historyRv.setAdapter(adapter);
        dialog.show();
    }

    private void openUpdatePopup(@NonNull ViewHolder holder, OrderItemsPojo o) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.update_order_dialog_layout);
        dialog.setCancelable(false);

        Button cancelBt = dialog.findViewById(R.id.cancel_bt);
        Button submitBt = dialog.findViewById(R.id.submit_bt);
        Spinner statusSp = dialog.findViewById(R.id.status_sp);
        TextView headingTv = dialog.findViewById(R.id.heading_tv);
        EditText commentsEt = dialog.findViewById(R.id.comment_et);

        headingTv.setText(context.getString(R.string.update_order));

        List<String> statusVals = new ArrayList<>();
        List<OrderStatusMaster> parentVals = ProjectConstants.orderParentStatusMasterMap.get(o.getStatusId());

        for(OrderStatusMaster s : parentVals){
            statusVals.add(s.getStatusVal());
        }

        ArrayAdapter adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, statusVals);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSp.setAdapter(adapter);

        cancelBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        submitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: call update order API
                if(statusSp.getSelectedItem() != null) {
                    if(commentsEt.getText() != null && commentsEt.getText().toString().length()>0) {

                        UpdateItemsPojo r = new UpdateItemsPojo();
                        r.setComments(commentsEt.getText().toString());
                        r.setDistId(o.getDistId());
                        r.setInvId(o.getInvId());
                        r.setStatusId(ProjectConstants.orderStatusNameMasterMap.get(statusSp.getSelectedItem().toString()).getStatusId());
                        r.setOrderId(o.getOrderId());
                        r.setQuantity(o.getQuantity());
                        r.setUpdatedBy(Integer.parseInt(userId));

                        holder.statusTv.setText(ProjectConstants.orderStatusMasterMap.get(r.getStatusId()).getStatusVal());
                        Boolean flag = true;
                        for(UpdateItemsPojo s : OrderLineItemsActivity.request){
                            if(s.getOrderId() == r.getOrderId() && s.getInvId() == r.getInvId() && s.getDistId() == r.getDistId()){
                                s.setStatusId(r.getStatusId());
                                flag = false;
                                break;
                            }
                        }
                        if(flag) {
                            OrderLineItemsActivity.request.add(r);
                        }

                        if(r.getStatusId() == 7){
                            UpdateItemsPojo r1 = new UpdateItemsPojo();
                            r1.setComments(commentsEt.getText().toString());
                            r1.setDistId(o.getDistId());
                            r1.setInvId(o.getInvId());
                            r1.setStatusId(o.getStatusId());
                            r1.setOrderId(o.getOrderId());
                            r1.setQuantity(o.getQuantity());
                            r1.setUpdatedBy(Integer.parseInt(userId));
                            Boolean flag2 = true;
                            for(UpdateItemsPojo s : OrderLineItemsActivity.receivedItems){
                                if(s.getOrderId() == r1.getOrderId() && s.getInvId() == r1.getInvId()
                                        && s.getDistId() == r1.getDistId()){
                                    OrderLineItemsActivity.receivedItems.remove(s);
                                    flag2 = false;
                                    break;
                                }
                            }
                            if(flag2) {
                                OrderLineItemsActivity.receivedItems.add(r1);
                            }
                        }
                        dialog.dismiss();

                    }else {
                        Toast.makeText(context,context.getString(R.string.please_add_some_comments), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, context.getString(R.string.please_select_status), Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView invId, invName, wId, wName, distId, distName, statusTv;
        CardView orderDetailsCv;
        ImageView historyIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            invId = itemView.findViewById(R.id.item_id_tv);
            invName = itemView.findViewById(R.id.item_name_tv);
            wId = itemView.findViewById(R.id.w_id_tv);
            wName = itemView.findViewById(R.id.w_name_tv);
            distId = itemView.findViewById(R.id.dist_id_tv);
            distName = itemView.findViewById(R.id.dist_name_tv);
            statusTv = itemView.findViewById(R.id.status_tv);
            historyIv = itemView.findViewById(R.id.history_iv);
            orderDetailsCv = itemView.findViewById(R.id.order_details_cv);
        }
    }

    private Boolean checkOrderStatus(OrderItemsPojo o){

        if(o.getStatusId() == 5){
            //Cancelled
            Toast.makeText(context,context.getString(R.string.item_cancelled), Toast.LENGTH_SHORT).show();
            return false;
        }else if(o.getStatusId() == 8){
            //Rejected
            Toast.makeText(context,context.getString(R.string.item_rejected), Toast.LENGTH_SHORT).show();
            return false;
        }else if(o.getStatusId() == 7){
            //Received
            Toast.makeText(context,context.getString(R.string.item_received), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}
