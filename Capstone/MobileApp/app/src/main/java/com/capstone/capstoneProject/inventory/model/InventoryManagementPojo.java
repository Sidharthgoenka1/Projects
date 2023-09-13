package com.capstone.capstoneProject.inventory.model;

import java.util.List;

public class InventoryManagementPojo {

    List<InventoryShopMapping> shopInvList;
    List<InventoryDistMapping> distInvList;

    public List<InventoryShopMapping> getShopInvList() {
        return shopInvList;
    }

    public void setShopInvList(List<InventoryShopMapping> shopInvList) {
        this.shopInvList = shopInvList;
    }

    public List<InventoryDistMapping> getDistInvList() {
        return distInvList;
    }

    public void setDistInvList(List<InventoryDistMapping> distInvList) {
        this.distInvList = distInvList;
    }

    @Override
    public String toString() {
        return "InventoryManagementPojo{" +
                "shopInvList=" + shopInvList +
                ", distInvList=" + distInvList +
                '}';
    }
}
