package com.solvd.services;

import com.solvd.dao.mySQL.CarDAO;
import com.solvd.dao.mySQL.UserDAO;
import com.solvd.models.Car;
import com.solvd.models.User;

import java.util.List;
import java.util.stream.Collectors;

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
