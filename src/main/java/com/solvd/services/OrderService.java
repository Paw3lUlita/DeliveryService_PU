package com.solvd.services;

import com.solvd.dao.mySQL.OrderDAO;
import com.solvd.dao.mySQL.UserDAO;
import com.solvd.models.Order;
import com.solvd.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    private OrderDAO orderDAO = new OrderDAO();

    public Order saveOrder(Order order) {
        return orderDAO.createEntity(order);
    }

    public Order getOrderById(long orderId) {
        return orderDAO.getEntityById(orderId);
    }

    public void updateOrder(Order order) {
        orderDAO.updateEntity(order);
    }

    public void deleteOrderById(long id) {
        orderDAO.removeEntity(id);
    }
}
