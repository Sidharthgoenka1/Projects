package com.capstone.capstoneProject.orderManagement.viewOrders;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
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
import com.capstone.capstoneProject.orderManagement.model.OrderDataWrapper;
import com.capstone.capstoneProject.orderManagement.model.OrderItemsPojo;
import com.capstone.capstoneProject.orderManagement.model.OrdersDataPojo;
import com.capstone.capstoneProject.orderManagement.model.UpdateInventoryPojo;
import com.capstone.capstoneProject.orderManagement.model.UpdateOrderRequest;
import com.capstone.capstoneProject.orderManagement.orderLineItems.OrderLineItemsActivity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewOrderAdapter extends RecyclerView.Adapter<ViewOrderAdapter.ViewHolder> {

    private OrderDataWrapper data;
    private Context context;
    private String userId, userType, username, shopId;

    public ViewOrderAdapter(OrderDataWrapper d, Context context, String t,String i, String u, String s) {
        data = d;
        this.context = context;
        userId = i;
        userType = t;
        username = u;
        shopId = s;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_order_card_view_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OrdersDataPojo s = data.getMessage().get(position);
        holder.orderId.setText(s.getOrderId()+"");
        holder.orderDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(s.getOrderDate()));
        holder.orderedBy.setText(s.getShopName());
        holder.destination.setText(s.getShopAddress());
//        holder.status.setText(s.getStatusVal());

        clickedAction(holder, position);
    }

    public void clickedAction(@NonNull ViewHolder holder, int position){

        holder.orderCv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Todo: view order history

                OrdersDataPojo o = data.getMessage().get(position);
                Intent intent = new Intent(context, OrderLineItemsActivity.class);
                intent.putExtra("orderId", o.getOrderId()+"");
                intent.putExtra("userId", userId);
                intent.putExtra("userType", userType);
                intent.putExtra("username", username);
                intent.putExtra("shopId", shopId);
                intent.putExtra("items",(Serializable) o.getItems());
                ((Activity)context).startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.getMessage().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView orderCv;
        TextView orderId, orderedBy, destination, orderDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.order_id_tv);
            orderedBy = itemView.findViewById(R.id.ordered_by_tv);
            destination = itemView.findViewById(R.id.destination_tv);
            orderDate = itemView.findViewById(R.id.order_date_tv);
            orderCv = itemView.findViewById(R.id.order_cv);
        }
    }
}
