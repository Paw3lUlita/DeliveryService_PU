package com.solvd.dao.mySQL;

import com.solvd.dao.interfaces.IDeliveryDAO;
import com.solvd.models.Address;
import com.solvd.models.Delivery;
import com.solvd.models.User;
import com.solvd.pool.ConnectionPool;

import java.sql.*;

public class DeliveryDAO extends AbstractMySQLDAO implements IDeliveryDAO {

    private static final String READ_DELIVERY_QUERY = "SELECT * FROM deliveries WHERE id = ?";

    private static final String CREATE_DELIVERY_QUERY =
            "INSERT INTO deliveries(user_id, address_id) VALUES (?, ?)";

    private static final String UPDATE_DELIVERY_QUERY =
            "UPDATE deliveries SET user_id=?, address_id=? WHERE id = ?";

    private static final String DELETE_DELIVERY_QUERY =
            "DELETE FROM deliveries WHERE id = ?";

    ConnectionPool pool = ConnectionPool.getInstance();
    UserDAO userDAO = new UserDAO();
    AddressDAO addressDAO = new AddressDAO();

    @Override
    public Delivery getEntityById(long deliveryId) {
        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(READ_DELIVERY_QUERY);
            statement.setLong(1, deliveryId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                int address_id = rs.getInt("address_id");

                int id = rs.getInt("id");
                User user = userDAO.getEntityById(userId);
                Address address = addressDAO.getEntityById(address_id);
                Delivery delivery = new Delivery(user, address);
                delivery.setId(id);
                pool.returnConnection(conn);
                return delivery;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateEntity(Delivery delivery) {
        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(UPDATE_DELIVERY_QUERY);
            statement.setLong(1, delivery.getUser().getId());
            statement.setLong(2, delivery.getAddress().getId());

            statement.executeUpdate();
            pool.returnConnection(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createEntity(Delivery delivery) {
        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement =
                    conn.prepareStatement(CREATE_DELIVERY_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, delivery.getUser().getId());
            statement.setLong(2, delivery.getAddress().getId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                delivery.setId(resultSet.getInt(1));
            }

            pool.returnConnection(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeEntity(long deliveryId) {
        try (Connection conn = pool.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_DELIVERY_QUERY);
            statement.setLong(1, deliveryId);
            statement.executeUpdate();
            pool.returnConnection(conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
