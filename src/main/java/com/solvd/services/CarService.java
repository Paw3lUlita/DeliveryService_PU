package com.solvd.services;

import com.solvd.dao.interfaces.ICarDAO;
import com.solvd.models.Car;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class CarService {
    SqlSessionFactory sqlSessionFactory;

    public CarService() {
        try {
            Reader reader = Resources.getResourceAsReader("myBatis_config.xml");
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Car getById(Long id) {
        Car car;
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICarDAO carDAO = sqlSession.getMapper(ICarDAO.class);
            car = carDAO.getEntityById(id);
        }
        return car;
    }

    public Car saveCar(Car car) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICarDAO carDAO = sqlSession.getMapper(ICarDAO.class);

            try {
                carDAO.createEntity(car);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }

    public void updateCar(Car car) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICarDAO carDAO = sqlSession.getMapper(ICarDAO.class);

            try {
                carDAO.updateEntity(car);
                sqlSession.commit();
            } catch (Exception e) {
                sqlSession.rollback();
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeCarById(long id) {
        try(SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ICarDAO carDAO = sqlSession.getMapper(ICarDAO.class);

            try {
                carDAO.removeEntity(id);
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
