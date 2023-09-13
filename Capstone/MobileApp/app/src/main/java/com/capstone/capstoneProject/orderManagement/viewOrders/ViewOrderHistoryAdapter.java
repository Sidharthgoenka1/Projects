package com.capstone.capstoneProject.orderManagement.viewOrders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.capstoneProject.ProjectConstants;
import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.orderManagement.model.OrderDetailsItemHistoryPojo;
import com.capstone.capstoneProject.orderManagement.model.OrderHistoryPojo;

import java.text.SimpleDateFormat;
import java.util.List;

public class ViewOrderHistoryAdapter extends RecyclerView.Adapter<ViewOrderHistoryAdapter.ViewHolder> {

    private List<OrderDetailsItemHistoryPojo> data;
    private Context context;

    public ViewOrderHistoryAdapter(List<OrderDetailsItemHistoryPojo> d, Context context) {
        data = d;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.order_history_card_view_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OrderDetailsItemHistoryPojo s = data.get(position);
        holder.userId.setText(s.getUpdatedBy()+"");
        holder.name.setText(s.getUsername());
        holder.status.setText(ProjectConstants.orderStatusMasterMap.get(s.getStatusId()).getStatusVal());
        holder.lastUpdateDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(s.getUpdateDate()));
        holder.comments.setText(s.getComments());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView userId, name, lastUpdateDate, status, comments;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.user_id_tv);
            name = itemView.findViewById(R.id.user_name_tv);
            lastUpdateDate = itemView.findViewById(R.id.update_date_tv);
            status = itemView.findViewById(R.id.status_tv);
            comments = itemView.findViewById(R.id.comment_tv);
        }
    }


}
