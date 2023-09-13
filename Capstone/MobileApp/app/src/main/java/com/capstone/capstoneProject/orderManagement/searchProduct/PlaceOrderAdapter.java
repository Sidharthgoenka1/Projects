package com.capstone.capstoneProject.orderManagement.searchProduct;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.orderManagement.model.AvailableDistForInventory;
import com.capstone.capstoneProject.orderManagement.model.CreateOrderRequestItems;

import java.util.List;

public class PlaceOrderAdapter extends RecyclerView.Adapter<PlaceOrderAdapter.ViewHolder> {

    private List<AvailableDistForInventory> dataList;
    private Context context;

    public PlaceOrderAdapter(List<AvailableDistForInventory> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.search_product_card_view_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        AvailableDistForInventory s = dataList.get(position);
        holder.itemName.setText(s.getInvName());
        holder.itemDesc.setText(s.getInvDesc());
        holder.distName.setText(s.getwName());
        holder.distAddress.setText(s.getwAddress());
        holder.distContact.setText(s.getwContact());
        cardClicked(holder, position);


    }

    public void cardClicked(ViewHolder holder, int position){
        holder.searchProdCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AvailableDistForInventory s = dataList.get(position);

                Dialog alertDialog = new Dialog(context);
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.setCancelable(false);
                alertDialog.setContentView(R.layout.item_detail_dialog_layout);


                TextView headingTv = alertDialog.findViewById(R.id.heading_tv);
                TextView invIdTv = alertDialog.findViewById(R.id.inv_id_tv);
                TextView invName = alertDialog.findViewById(R.id.inv_name_tv);
                TextView distIdTv = alertDialog.findViewById(R.id.dist_id_tv);
                TextView distNameTv = alertDialog.findViewById(R.id.dist_name_tv);
                TextView warehouseIdTv = alertDialog.findViewById(R.id.w_id_tv);
                TextView warehouseNameTv = alertDialog.findViewById(R.id.w_name_tv);
                TextView warehouseAddressTv = alertDialog.findViewById(R.id.w_address_tv);
                TextView warehouseContactTv = alertDialog.findViewById(R.id.w_contact_tv);
                TextView availableQtyTv = alertDialog.findViewById(R.id.available_qty_tv);
                EditText qtyEt = alertDialog.findViewById(R.id.qty_et);
                EditText commentEt = alertDialog.findViewById(R.id.comment_et);
                Button cancelBt = alertDialog.findViewById(R.id.cancel_bt);
                Button addBt = alertDialog.findViewById(R.id.add_bt);

                headingTv.setText(context.getString(R.string.item));
                invIdTv.setText(s.getInvId());
                invName.setText(s.getInvName());
                distIdTv.setText(s.getDistId());
                distNameTv.setText(s.getDistName());
                warehouseIdTv.setText(s.getwId()+"");
                warehouseNameTv.setText(s.getwName());
                warehouseAddressTv.setText(s.getwAddress());
                warehouseContactTv.setText(s.getwContact());
                availableQtyTv.setText(s.getQuantity()+"");

                cancelBt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                addBt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(qtyEt.getText() != null &&
                                qtyEt.getText().toString().length()>0 &&
                                Integer.parseInt(qtyEt.getText().toString())>0){
                            if(Integer.parseInt(qtyEt.getText().toString()) <= s.getQuantity()){
                                CreateOrderRequestItems item = new CreateOrderRequestItems();
                                item.setDistId(Integer.parseInt(s.getDistId()));
                                item.setDistName(s.getDistName());
                                item.setInvId(Integer.parseInt(s.getInvId()));
                                item.setInvName(s.getInvName());
                                item.setStatusId(1);
                                item.setQuantity(Integer.parseInt(qtyEt.getText().toString()));
                                item.setOrderDescription(commentEt.getText().toString());

                                if(PlaceOrderActivity.createOrderRequestWrapper.getMessage()
                                        .getCreateOrderRequestItemList().size()>0){
                                    for(CreateOrderRequestItems c : PlaceOrderActivity.createOrderRequestWrapper.getMessage()
                                            .getCreateOrderRequestItemList()){
                                        if(c.getInvName().equalsIgnoreCase(item.getInvName())){
                                            PlaceOrderActivity.createOrderRequestWrapper.getMessage()
                                                    .getCreateOrderRequestItemList().remove(c);
                                            PlaceOrderActivity.createOrderRequestWrapper.getMessage()
                                                    .getCreateOrderRequestItemList().add(item);
                                        }
                                    }
                                }else {
                                    PlaceOrderActivity.createOrderRequestWrapper.getMessage()
                                            .getCreateOrderRequestItemList().add(item);
                                }
                                Toast.makeText(context, context.getString(R.string.item_added_cart), Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();
                            }else {
                                Toast.makeText(context, context.getString(R.string.qty_greater_entered)+" "+s.getQuantity(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(context, context.getString(R.string.qty_not_entered), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView searchProdCv;
        TextView itemName, itemDesc, distName, distAddress, distContact;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemDesc = itemView.findViewById(R.id.item_desc);
            distName = itemView.findViewById(R.id.dist_name);
            distAddress = itemView.findViewById(R.id.dist_address);
            distContact = itemView.findViewById(R.id.dist_contact);
            searchProdCv = itemView.findViewById(R.id.search_prod_cv);
        }
    }
}
