package com.brisbiere.Brisbiere.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Order {
    private int orderId;
    private String trackingGuide;
    private String status;
    private String address;
    private Timestamp date;
    private int userId;
    private String payment;
    private BigDecimal total;
    private List<ProductOrder> items;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTrackingGuide() {
        return trackingGuide;
    }

    public void setTrackingGuide(String trackingGuide) {
        this.trackingGuide = trackingGuide;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ProductOrder> getItems() {
        return items;
    }

    public void setItems(List<ProductOrder> items) {
        this.items = items;
    }


}
