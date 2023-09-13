package com.capstone.capstoneProject.orderManagement.model;

import java.util.List;

public class OrderLineItemWrapper {

    private int statusCode;
    private String status;
    private List<OrderItemsPojo> message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderItemsPojo> getMessage() {
        return message;
    }

    public void setMessage(List<OrderItemsPojo> message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OrderLineItemWrapper{" +
                "statusCode=" + statusCode +
                ", status='" + status + '\'' +
                ", message=" + message +
                '}';
    }
}
