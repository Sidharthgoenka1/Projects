package com.capstone.capstoneProject.dashboard.model;

public class OrderStatusMaster {
    private Integer statusId;
    private String statusVal;
    private Integer parentId;

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusVal() {
        return statusVal;
    }

    public void setStatusVal(String statusVal) {
        this.statusVal = statusVal;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "OrderStatusMaster{" +
                "statusId=" + statusId +
                ", statusVal='" + statusVal + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
