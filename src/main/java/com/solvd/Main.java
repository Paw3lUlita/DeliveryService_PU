package com.solvd;

import com.solvd.dao.mySQL.AddressDAO;
import com.solvd.dao.mySQL.UserDAO;
import com.solvd.models.Address;
import com.solvd.models.User;
import com.solvd.services.AddressService;
import com.solvd.services.DeliveryService;
import com.solvd.services.OrderService;
import com.solvd.services.UserService;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        DeliveryService deliveryService = new DeliveryService();
        AddressService addressService = new AddressService();

        User user = userService.getUserById(1);
        Address address = addressService.getAddressById(9);
        orderService.addOrderForUser(user, address);

        orderService.getAllOrders().forEach(System.out::println);
    }
}
