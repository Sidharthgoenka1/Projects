package com.capstone.capstoneProject.orderManagement.model;

public class SearchProductModel {

    private Integer invId;
    private String itemName;
    private String itemDesc;
    private String distName;
    private String distId;
    private String distAddress;
    private String distContact;

    public Integer getInvId() {
        return invId;
    }

    public void setInvId(Integer invId) {
        this.invId = invId;
    }

    @Override
    public String toString() {
        return "SearchProductModel{" +
                "invId=" + invId +
                ", itemName='" + itemName + '\'' +
                ", itemDesc='" + itemDesc + '\'' +
                ", distName='" + distName + '\'' +
                ", distId='" + distId + '\'' +
                ", distAddress='" + distAddress + '\'' +
                ", distContact='" + distContact + '\'' +
                '}';
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    public String getDistId() {
        return distId;
    }

    public void setDistId(String distId) {
        this.distId = distId;
    }

    public String getDistAddress() {
        return distAddress;
    }

    public void setDistAddress(String distAddress) {
        this.distAddress = distAddress;
    }

    public String getDistContact() {
        return distContact;
    }

    public void setDistContact(String distContact) {
        this.distContact = distContact;
    }
}
