package com.solvd.services;

import com.solvd.dao.mySQL.OrderDAO;
import com.solvd.models.Address;
import com.solvd.models.Delivery;
import com.solvd.models.Order;
import com.solvd.models.User;

import java.util.List;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();
    private DeliveryService deliveryService = new DeliveryService();

    public Order saveOrder(Order order) {
        return orderDAO.createEntity(order);
    }

    public void addOrderForUser(User user, Address address) {
        Delivery delivery = new Delivery(user, address);
        Order order = new Order(user, deliveryService.saveDelivery(delivery));
        saveOrder(order);
    }

    public Order getOrderById(long orderId) {
        return orderDAO.getEntityById(orderId);
    }

    public List<Order> getAllOrders() {
        return orderDAO.getAllOrders();
    }

    public void updateOrder(Order order) {
        orderDAO.updateEntity(order);
    }

    public void deleteOrderById(long id) {
        orderDAO.removeEntity(id);
    }
}
