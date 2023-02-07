package com.solvd.services;

import com.solvd.dao.interfaces.IAddressDAO;
import com.solvd.dao.interfaces.IUserDAO;
import com.solvd.dao.mySQL.UserDAO;
import com.solvd.models.Address;
import com.solvd.models.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class UserService {
    SqlSessionFactory sqlSessionFactory;
    public UserService() {
        try {
            Reader reader = Resources.getResourceAsReader("myBatis_config.xml");
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public User getById(Long id) {
        User user;
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO userDAO = sqlSession.getMapper(IUserDAO.class);
            user = userDAO.getEntityById(id);
        }
        return user;
    }
}
