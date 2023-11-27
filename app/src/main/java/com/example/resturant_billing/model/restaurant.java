package com.example.resturant_billing.model;

public class restaurant {

    private Long id;

    private String name;

    private String gst;

    private String address;

    private Long ownerid;

    public restaurant(){
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

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(Long ownerid) {
        this.ownerid = ownerid;
    }

    @Override
    public String toString() {
        return "restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gst='" + gst + '\'' +
                ", address='" + address + '\'' +
                ", ownerid=" + ownerid +
                '}';
    }
}
