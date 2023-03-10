package com.solvd;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.dao.mySQL.AddressDAO;
import com.solvd.dao.mySQL.UserDAO;
import com.solvd.models.Address;
import com.solvd.models.User;
import com.solvd.services.AddressService;
import com.solvd.services.DeliveryService;
import com.solvd.services.OrderService;
import com.solvd.services.UserService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserService();
        AddressService addressService = new AddressService();
        OrderService orderService = new OrderService();
        DeliveryService deliveryService = new DeliveryService();

        Address address = userService.getById(1L).getAddress();
        address.setHouse_number(111);
        addressService.updateAddress(address);

        System.out.println(addressService.getById(address.getId()));

        System.out.println(orderService.getById(4L));
        System.out.println(userService.getById(1L));
        System.out.println(deliveryService.getById(3L));

        userService.getAllUsers().forEach(System.out::println);

    }
}
