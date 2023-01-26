package com.solvd.models;

public class Order {
    private long id;
    private User user;
    private Delivery delivery;

    public Order(User user, Delivery delivery) {
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user.getName()+" "+user.getSurname() +
                ", delivery=" + delivery.getAddress() +
                '}';
    }
}
