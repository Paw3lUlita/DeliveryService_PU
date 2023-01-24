package com.solvd.models;

public class Order {
    private long id;
    private User user;
    private Delivery delivery;

    public Order(long id, User user, Delivery delivery) {
        this.id = id;
        this.user = user;
        this.delivery = delivery;
    }

    public Order() {};

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

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
