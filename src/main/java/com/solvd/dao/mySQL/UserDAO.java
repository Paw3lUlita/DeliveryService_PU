package com.solvd.dao.mySQL;

import com.solvd.dao.interfaces.IUserDAO;
import com.solvd.models.Address;
import com.solvd.models.User;
import com.solvd.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO extends AbstractMySQLDAO implements IUserDAO {

    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";

    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(name, surname, phone, email, address_id) VALUES (?, ?, ?, ?, ?)";

    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET name = ?, surname=?, phone=?, email= ?, address_id=? WHERE id = ?";

    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";

    private static final String FIND_ALL_QUERY =
            "SELECT * FROM users";
    ConnectionPool pool = ConnectionPool.getInstance();
    AddressDAO addressDAO = new AddressDAO();

    @Override
    public User getEntityById(long userId) {

        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phoneNumber = rs.getString("phone");
                String email = rs.getString("email");
                long addressId = rs.getLong("address_id");

                int id = rs.getInt("id");
                Address address = addressDAO.getEntityById(addressId);
                User user1 = new User(name, surname, phoneNumber, email, address);
                user1.setId(id);
                return user1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateEntity(User user) {
        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getEmail());
            statement.setLong(5, user.getAddress().getId());
            statement.setLong(6, user.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User createEntity(User user) {
        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPhoneNumber());
            statement.setString(4, user.getEmail());
            statement.setLong(5, user.getAddress().getId());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }

            return user;

        } catch (SQLException e) {

            e.printStackTrace();

            return null;

        }
    }

    @Override
    public void removeEntity(long userId) {
        try (Connection conn = pool.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setLong(1, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Connection conn = pool.getConnection()) {
            List<User> allUsers = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_QUERY);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String phoneNumber = rs.getString("phone");
                String email = rs.getString("email");
                long addressId = rs.getLong("address_id");

                int id = rs.getInt("id");
                Address address = addressDAO.getEntityById(addressId);
                User user1 = new User(name, surname, phoneNumber, email, address);
                user1.setId(id);
                allUsers.add(user1);

            }
            return allUsers;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
