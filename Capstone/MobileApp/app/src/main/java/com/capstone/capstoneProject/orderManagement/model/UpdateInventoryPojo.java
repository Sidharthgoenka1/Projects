package com.capstone.capstoneProject.orderManagement.model;

public class UpdateInventoryPojo {
    private Integer invId;
    private Integer distId;
    private Integer quantity;
    public Integer getInvId() {
        return invId;
    }
    public void setInvId(Integer invId) {
        this.invId = invId;
    }
    public Integer getDistId() {
        return distId;
    }
    public void setDistId(Integer distId) {
        this.distId = distId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @Override
    public String toString() {
        return "UpdateInventoryPojo [invId=" + invId + ", distId=" + distId + ", quantity=" + quantity + "]";
    }
}
