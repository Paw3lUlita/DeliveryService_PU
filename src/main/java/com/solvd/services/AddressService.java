package com.solvd.services;

import com.solvd.dao.interfaces.IAddressDAO;
import com.solvd.models.Address;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class AddressService {
    SqlSessionFactory sqlSessionFactory;

    public AddressService() {
        try {
            Reader reader = Resources.getResourceAsReader("myBatis_config.xml");
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Address getById(Long id) {
        Address address;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAddressDAO addressDAO = sqlSession.getMapper(IAddressDAO.class);
            address = addressDAO.getEntityById(id);
        }
        return address;
    }

    public Address saveAddress(Address address) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAddressDAO addressDAO = sqlSession.getMapper(IAddressDAO.class);

            try {
                addressDAO.createEntity(address);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    public void updateAddress(Address address) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAddressDAO addressDAO = sqlSession.getMapper(IAddressDAO.class);

            try {
                addressDAO.updateEntity(address);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeAddressById(long id) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAddressDAO addressDAO = sqlSession.getMapper(IAddressDAO.class);

            try {
                addressDAO.removeEntity(id);
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
