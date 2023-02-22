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
        User u = new User();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            u = objectMapper.readValue(new File("src/main/resources/json_files/user.json"), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("User mapped by Jackson: ");
        System.out.println(u);
        System.out.println("His address: ");
        System.out.println(u.getAddress());
    }
}
