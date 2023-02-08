package com.solvd.services;

import com.solvd.dao.interfaces.IAddressDAO;
import com.solvd.dao.interfaces.IDeliveryDAO;
import com.solvd.dao.mySQL.DeliveryDAO;
import com.solvd.models.Address;
import com.solvd.models.Delivery;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


public class DeliveryService {
    SqlSessionFactory sqlSessionFactory;

    public DeliveryService() {
        try {
            Reader reader = Resources.getResourceAsReader("myBatis_config.xml");
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Delivery getById(Long id) {
        Delivery delivery;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDeliveryDAO deliveryDAO = sqlSession.getMapper(IDeliveryDAO.class);
            delivery = deliveryDAO.getEntityById(id);
        }
        return delivery;
    }

    public Delivery saveDelivery(Delivery delivery) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDeliveryDAO deliveryDAO = sqlSession.getMapper(IDeliveryDAO.class);

            try {
                deliveryDAO.createEntity(delivery);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return delivery;
    }

    public void updateDelivery(Delivery delivery) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDeliveryDAO deliveryDAO = sqlSession.getMapper(IDeliveryDAO.class);

            try {
                deliveryDAO.updateEntity(delivery);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeDelivery(long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IDeliveryDAO deliveryDAO = sqlSession.getMapper(IDeliveryDAO.class);

            try {
                deliveryDAO.removeEntity(id);
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
