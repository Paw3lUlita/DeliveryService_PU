package com.solvd;

import com.solvd.dao.mySQL.AddressDAO;
import com.solvd.dao.mySQL.UserDAO;
import com.solvd.models.Address;
import com.solvd.models.User;

public class Main {
    public static void main(String[] args) {
        AddressDAO addressDAO = new AddressDAO();
        UserDAO userDAO = new UserDAO();
        Address address = addressDAO.getEntityById(8);
        User user = new User("Pawel", "Ulita",
                "56786565", "opkokokok", address);

        user = userDAO.createEntity(user);
        System.out.println(user);
        System.out.println(user.getAddress());
    }
}
