package com.brisbiere.Brisbiere.domain;

import java.math.BigDecimal;

public class ProductOrder {

    private int id;
    private int orderId;
    private int productId;
    private String name;
    private int quantity;
    private BigDecimal beerPrice;
    private BigDecimal beerTotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getBeerPrice() {
        return beerPrice;
    }

    public void setBeerPrice(BigDecimal beerPrice) {
        this.beerPrice = beerPrice;
    }

    public BigDecimal getBeerTotal() {
        return beerTotal;
    }

    public void setBeerTotal(BigDecimal beerTotal) {
        this.beerTotal = beerTotal;
    }
}
