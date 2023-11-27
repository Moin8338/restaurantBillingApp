package com.example.resturant_billing.model;

public class order_item {

    private int id;

    private String name;

    private int price;

    private String category;

    private int qty;

    private int total;

    public order_item() {
    }

    public order_item(int id, String name, int price,String category, int qty, int total) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category=category;
        this.qty = qty;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "order_item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", qty=" + qty +
                ", total=" + total +
                '}';
    }
}
