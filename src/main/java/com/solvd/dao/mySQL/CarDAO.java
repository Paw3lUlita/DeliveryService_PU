package com.solvd.dao.mySQL;

import com.solvd.dao.interfaces.ICarDAO;
import com.solvd.models.Address;
import com.solvd.models.Car;
import com.solvd.models.User;
import com.solvd.pool.ConnectionPool;

import java.sql.*;

public class CarDAO extends AbstractMySQLDAO implements ICarDAO {

    private static final String READ_CAR_QUERY = "SELECT * FROM cars WHERE id = ?";

    private static final String CREATE_CAR_QUERY =
            "INSERT INTO cars(model, capacity) VALUES (?, ?)";

    private static final String UPDATE_CAR_QUERY =
            "UPDATE cars SET model=?, capacity=? WHERE id = ?";

    private static final String DELETE_CAR_QUERY =
            "DELETE FROM cars WHERE id = ?";

    ConnectionPool pool = ConnectionPool.getInstance();
    @Override
    public Car getEntityById(long carId) {
        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(READ_CAR_QUERY);
            statement.setLong(1, carId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String model = rs.getString("name");
                int capacity = rs.getInt("capacity");
                int id = rs.getInt("id");

                Car car = new Car(model, capacity);
                car.setId(id);
                pool.returnConnection(conn);
                return car;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateEntity(Car car) {
        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(UPDATE_CAR_QUERY);
            statement.setString(1, car.getModel());
            statement.setInt(2, car.getCapacity());

            statement.executeUpdate();
            pool.returnConnection(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createEntity(Car car) {
        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement =
                    conn.prepareStatement(CREATE_CAR_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, car.getModel());
            statement.setInt(2, car.getCapacity());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
               car.setId(resultSet.getInt(1));
            }

            pool.returnConnection(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeEntity(long carId) {
        try (Connection conn = pool.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_CAR_QUERY);
            statement.setLong(1, carId);
            statement.executeUpdate();
            pool.returnConnection(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
