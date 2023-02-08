package com.solvd.services;

import com.solvd.dao.interfaces.IUserDAO;
import com.solvd.models.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

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

    public List<User> getAllUsers() {
        List<User> resulList;
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO userDAO = sqlSession.getMapper(IUserDAO.class);
            resulList = userDAO.getAllUsers();
        }
        return resulList;
    }

    public User saveUser(User user) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO userDAO = sqlSession.getMapper(IUserDAO.class);
            try {
                userDAO.createEntity(user);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public void updateUser(User user) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO userDAO = sqlSession.getMapper(IUserDAO.class);
            try {
                userDAO.updateEntity(user);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO userDAO = sqlSession.getMapper(IUserDAO.class);
            try {
                userDAO.removeEntity(id);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
