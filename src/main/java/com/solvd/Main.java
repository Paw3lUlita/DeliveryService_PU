package com.solvd;

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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Document doc = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = builder.parse("src/main/resources/xml_files/user.xml");
            Node userNode = doc.getElementsByTagName("user").item(0);
            System.out.println("user id: " + userNode.getAttributes().getNamedItem("id").getTextContent());
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        System.out.println("name: " + doc.getElementsByTagName("name").item(0).getTextContent());
        System.out.println("surname: " + doc.getElementsByTagName("surname").item(0).getTextContent());
        System.out.println("phoneNumber: " + doc.getElementsByTagName("phoneNumber").item(0).getTextContent());
        System.out.println("email: " + doc.getElementsByTagName("email").item(0).getTextContent());
        System.out.println("Address: ");
        System.out.println("address id: " + doc.getElementsByTagName("address").item(0)
                .getAttributes().getNamedItem("id").getTextContent());
        System.out.println(doc.getElementsByTagName("street").item(0).getTextContent());
        System.out.println(doc.getElementsByTagName("house_number").item(0).getTextContent());
        System.out.println(doc.getElementsByTagName("postcode").item(0).getTextContent());
    }
}
