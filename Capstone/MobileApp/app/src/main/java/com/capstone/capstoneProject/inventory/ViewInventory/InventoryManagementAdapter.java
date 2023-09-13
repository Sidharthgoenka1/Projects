package com.capstone.capstoneProject.inventory.ViewInventory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.capstone.capstoneProject.ProjectConstants;
import com.capstone.capstoneProject.R;
import com.capstone.capstoneProject.dashboard.model.InventoryMaster;
import com.capstone.capstoneProject.inventory.model.InventoryDistMapping;
import com.capstone.capstoneProject.inventory.model.InventoryManagementPojo;
import com.capstone.capstoneProject.inventory.model.InventoryShopMapping;

public class InventoryManagementAdapter extends RecyclerView.Adapter<InventoryManagementAdapter.ViewHolder> {

    private InventoryManagementPojo data;
    private Context context;

    public InventoryManagementAdapter(InventoryManagementPojo d, Context context) {
        data = d;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).
                inflate(R.layout.inv_management_card_view_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(data.getShopInvList() != null && data.getShopInvList().size()>0){
            InventoryShopMapping shopMapping = data.getShopInvList().get(position);
            holder.itemQty.setText(shopMapping.getQuantity()+"");
            InventoryMaster master = ProjectConstants.invIdMasterMap.get(shopMapping.getMedId());
            holder.itemId.setText(master.getId()+"");
            holder.itemDesc.setText(master.getDescription());
            holder.itemSalts.setText(master.getSalts());
            holder.itemName.setText(master.getName());
            if(shopMapping.getQuantity()<100){
                holder.itemQty.setTextColor(context.getColor(R.color.red));
            }
        }else if(data.getDistInvList() != null && data.getDistInvList().size()>0){
            InventoryDistMapping distMapping = data.getDistInvList().get(position);
            holder.itemQty.setText(distMapping.getQuantity()+"");
            InventoryMaster master = ProjectConstants.invIdMasterMap.get(distMapping.getMedId());
            holder.itemId.setText(master.getId()+"");
            holder.itemDesc.setText(master.getDescription());
            holder.itemSalts.setText(master.getSalts());
            holder.itemName.setText(master.getName());
            if(distMapping.getQuantity()<100){
                holder.itemQty.setTextColor(context.getColor(R.color.red));
            }
        }else {

        }

    }

    @Override
    public int getItemCount() {
        if(data.getDistInvList() != null && data.getDistInvList().size()>0){
            return data.getDistInvList().size();
        }else if(data.getShopInvList() != null && data.getShopInvList().size()>0){
            return data.getShopInvList().size();
        }else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView invManagementCV;
        TextView itemName, itemDesc, itemSalts, itemQty, itemId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name_tv);
            itemDesc = itemView.findViewById(R.id.item_desc_tv);
            itemQty = itemView.findViewById(R.id.item_qty_tv);
            itemSalts = itemView.findViewById(R.id.item_salts_tv);
            itemId = itemView.findViewById(R.id.item_id_tv);
            invManagementCV = itemView.findViewById(R.id.inv_management_cv);
        }
    }
}
