package com.solvd.services;

import com.solvd.dao.mySQL.DeliveryDAO;
import com.solvd.models.Delivery;



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
