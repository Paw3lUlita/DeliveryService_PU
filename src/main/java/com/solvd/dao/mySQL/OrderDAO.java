package com.solvd.dao.mySQL;

import com.solvd.dao.interfaces.IOrderDAO;
import com.solvd.models.Address;
import com.solvd.models.Delivery;
import com.solvd.models.Order;
import com.solvd.models.User;
import com.solvd.pool.ConnectionPool;

import java.sql.*;

public class OrderDAO extends AbstractMySQLDAO implements IOrderDAO {
    private static final String READ_ORDER_QUERY = "SELECT * FROM orders WHERE id = ?";

    private static final String CREATE_ORDER_QUERY =
            "INSERT INTO orders(user_id, delivery_id) VALUES (?, ?)";

    private static final String UPDATE_ORDER_QUERY =
            "UPDATE orders SET user_id=?, delivery_id=? WHERE id = ?";

    private static final String DELETE_ORDER_QUERY =
            "DELETE FROM orders WHERE id = ?";

    ConnectionPool pool = ConnectionPool.getInstance();
    UserDAO userDAO = new UserDAO();
    DeliveryDAO deliveryDAO = new DeliveryDAO();

    @Override
    public Order getEntityById(long orderId) {
        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(READ_ORDER_QUERY);
            statement.setLong(1, orderId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                int deliveryId = rs.getInt("delivery_id");

                int id = rs.getInt("id");
                User user = userDAO.getEntityById(userId);
                Delivery delivery = deliveryDAO.getEntityById(deliveryId);
                Order order = new Order(user, delivery);
                order.setId(id);
                return order;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateEntity(Order order) {
        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(UPDATE_ORDER_QUERY);
            statement.setLong(1, order.getUser().getId());
            statement.setLong(2, order.getDelivery().getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order createEntity(Order order) {
        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement =
                    conn.prepareStatement(CREATE_ORDER_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setLong(1, order.getUser().getId());
            statement.setLong(2, order.getDelivery().getId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                order.setId(resultSet.getInt(1));
            }

            return order;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeEntity(long orderId) {
        try (Connection conn = pool.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_ORDER_QUERY);
            statement.setLong(1, orderId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
