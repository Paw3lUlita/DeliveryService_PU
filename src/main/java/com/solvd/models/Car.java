package com.solvd.models;

public class Car {
    private long id;
    private String model;
    private int capacity;

    public Car(String model, int capacity) {
        this.model = model;
        this.capacity = capacity;
    }

    public Car() {};

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
