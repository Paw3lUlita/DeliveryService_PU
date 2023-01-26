package com.solvd.services;

import com.solvd.dao.mySQL.CarDAO;
import com.solvd.models.Car;

public class CarService {

    private CarDAO carDAO = new CarDAO();

    public Car saveCar(Car car) {
        return carDAO.createEntity(car);
    }

    public Car getCarById(long carId) {
        return carDAO.getEntityById(carId);
    }

    public void updateCar(Car car) {
        carDAO.updateEntity(car);
    }

    public void deleteCarById(long id) {
        carDAO.removeEntity(id);
    }
}
