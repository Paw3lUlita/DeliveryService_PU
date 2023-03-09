package com.solvd.services;

import com.solvd.dao.interfaces.IOrderDAO;
import com.solvd.models.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class OrderService {
    SqlSessionFactory sqlSessionFactory;

    public OrderService() {
        try {
            Reader reader = Resources.getResourceAsReader("myBatis_config.xml");
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Order getById(Long id) {
        Order order;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IOrderDAO orderDAO = sqlSession.getMapper(IOrderDAO.class);
            order = orderDAO.getEntityById(id);
        }
        return order;
    }

    public Order saveOrder(Order order) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IOrderDAO orderDAO = sqlSession.getMapper(IOrderDAO.class);

            try {
                orderDAO.createEntity(order);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public void updateOrder(Order order) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IOrderDAO orderDAO = sqlSession.getMapper(IOrderDAO.class);

            try {
                orderDAO.updateEntity(order);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeOrder(long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IOrderDAO orderDAO = sqlSession.getMapper(IOrderDAO.class);

            try {
                orderDAO.removeEntity(id);
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
