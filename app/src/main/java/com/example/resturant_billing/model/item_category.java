package com.example.resturant_billing.model;

public class item_category {

    private Long id;

    private String name;

    public item_category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "item_category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
