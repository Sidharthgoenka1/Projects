package com.capstone.capstoneProject.composite.pojos;
public class UpdateInventoryRequestPojo {

    private String itemName;
    private String itemDesc;
    private String shortDesc;
    private String itemSalts;
    private Integer itemId;
    private Integer qty;
    private Integer workplaceId;

    @Override
    public String toString() {
        return "UpdateInventoryRequestPojo{" +
                "itemName='" + itemName + '\'' +
                ", itemDesc='" + itemDesc + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", itemSalts='" + itemSalts + '\'' +
                ", itemId=" + itemId +
                ", qty=" + qty +
                ", workplaceId=" + workplaceId +
                '}';
    }

    public Integer getWorkplaceId() {
        return workplaceId;
    }

    public void setWorkplaceId(Integer workplaceId) {
        this.workplaceId = workplaceId;
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

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getItemSalts() {
        return itemSalts;
    }

    public void setItemSalts(String itemSalts) {
        this.itemSalts = itemSalts;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}