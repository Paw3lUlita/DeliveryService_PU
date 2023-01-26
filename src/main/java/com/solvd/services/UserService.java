package com.solvd.services;

import com.solvd.dao.mySQL.UserDAO;
import com.solvd.models.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private UserDAO userDAO = new UserDAO();

    public User saveUser(User user) {
        return userDAO.createEntity(user);
    }

    public User getUserById(long userId) {
        return userDAO.getEntityById(userId);
    }

    public List<User> getUsersByName(String userName) {
        return userDAO.getAllUsers().stream()
                .filter(user -> user.getName().equals(userName))
                .collect(Collectors.toList());
    }

    public void updateUser (User user) {
        userDAO.updateEntity(user);
    }

    public void deleteUserById(long id) {
        userDAO.removeEntity(id);
    }
}
