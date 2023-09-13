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
import com.capstone.capstoneProject.orderManagement.model.OrderHistoryPojo;
import com.capstone.capstoneProject.orderManagement.model.OrderItemsPojo;

import java.text.SimpleDateFormat;
import java.util.List;

public class ViewOrderDetailsAdapter extends RecyclerView.Adapter<ViewOrderDetailsAdapter.ViewHolder> {

    private List<OrderItemsPojo> data;
    private Context context;

    public ViewOrderDetailsAdapter(List<OrderItemsPojo> d, Context context) {
        data = d;
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView invId, invName, wId, wName, distId, distName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            invId = itemView.findViewById(R.id.item_id_tv);
            invName = itemView.findViewById(R.id.item_name_tv);
            wId = itemView.findViewById(R.id.w_id_tv);
            wName = itemView.findViewById(R.id.w_name_tv);
            distId = itemView.findViewById(R.id.dist_id_tv);
            distName = itemView.findViewById(R.id.dist_name_tv);
        }
    }


}
