package com.capstone.capstoneProject.orderManagement.searchProduct;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.capstoneProject.ProjectConstants;
import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.orderManagement.model.CreateOrderRequestItems;
import com.capstone.capstoneProject.orderManagement.model.CreateOrderRequestWrapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewCartAdapter extends RecyclerView.Adapter<ViewCartAdapter.ViewHolder> {

    private CreateOrderRequestWrapper data;
    private Context context;

    public ViewCartAdapter(CreateOrderRequestWrapper d, Context context) {
        data = d;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_cart_card_view_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CreateOrderRequestItems s = data.getMessage().getCreateOrderRequestItemList().get(position);
        holder.itemName.setText(s.getInvName());
        holder.itemQty.setText(context.getString(R.string.qty)+" :- "+s.getQuantity());
        holder.distName.setText(s.getDistName());
        holder.status.setText(ProjectConstants.orderStatusMasterMap.get(s.getStatusId()).getStatusVal());
        holder.orderDate.setText(context.getString(R.string.order_date)+" :- "+new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
        removeItem(holder, position);
    }

    public void removeItem(@NonNull ViewHolder holder, int position){
        holder.deleteIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateOrderRequestItems s = data.getMessage().getCreateOrderRequestItemList().get(position);
                PlaceOrderActivity.createOrderRequestWrapper.getMessage().getCreateOrderRequestItemList().remove(s);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.getMessage().getCreateOrderRequestItemList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cartCv;
        TextView itemName, itemQty, distName, orderDate, status;
        ImageView deleteIv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemQty = itemView.findViewById(R.id.item_qty);
            distName = itemView.findViewById(R.id.dist_name);
            orderDate = itemView.findViewById(R.id.order_date);
            status = itemView.findViewById(R.id.status_tv);
            deleteIv = itemView.findViewById(R.id.remove_iv);
            cartCv = itemView.findViewById(R.id.cart_cv);
        }
    }


}
