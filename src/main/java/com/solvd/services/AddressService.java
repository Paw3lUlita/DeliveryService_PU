package com.solvd.services;

import com.solvd.dao.mySQL.AddressDAO;
import com.solvd.dao.mySQL.UserDAO;
import com.solvd.models.Address;
import com.solvd.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class AddressService {
    private AddressDAO addressDAO = new AddressDAO();

    public Address saveAddress(Address address) {
        return addressDAO.createEntity(address);
    }

    public Address getAddressById(long addressId) {
        return addressDAO.getEntityById(addressId);
    }

    public void updateAddress(Address address) {
        addressDAO.updateEntity(address);
    }

    public void deleteAddressById(long id) {
        addressDAO.removeEntity(id);
    }
}
