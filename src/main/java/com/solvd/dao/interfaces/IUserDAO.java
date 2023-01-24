package com.solvd.dao.interfaces;

import com.solvd.models.User;

import java.util.List;

public interface IUserDAO extends IBaseDAO<User>{
    List<User> getAllUsers();
}
