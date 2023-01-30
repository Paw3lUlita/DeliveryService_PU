package com.solvd.models;

public class Delivery {
    private long id;
    private User user;
    private Address address;

    public Delivery(User user, Address address) {
        this.user = user;
        this.address = address;
    }

    public Delivery() {};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
