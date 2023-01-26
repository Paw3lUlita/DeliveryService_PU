package com.solvd.services;

import com.solvd.dao.mySQL.DeliveryDAO;
import com.solvd.dao.mySQL.UserDAO;
import com.solvd.models.Delivery;
import com.solvd.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class DeliveryService {
    private DeliveryDAO deliveryDAO = new DeliveryDAO();

    public Delivery saveDelivery(Delivery delivery) {
        return deliveryDAO.createEntity(delivery);
    }

    public Delivery getDeliveryById(long deliveryId) {
        return deliveryDAO.getEntityById(deliveryId);
    }

    public void updateDelivery(Delivery delivery) {
        deliveryDAO.updateEntity(delivery);
    }

    public void deleteDeliveryById(long id) {
        deliveryDAO.removeEntity(id);
    }
}
