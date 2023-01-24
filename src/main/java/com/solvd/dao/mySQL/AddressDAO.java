package com.solvd.dao.mySQL;

import com.solvd.dao.interfaces.IAddressDAO;
import com.solvd.models.Address;
import com.solvd.pool.ConnectionPool;

import java.sql.*;

public class AddressDAO extends AbstractMySQLDAO implements IAddressDAO {

    private static final String READ_ADDRESS_QUERY = "SELECT * FROM addresses WHERE id = ?";

    private static final String CREATE_ADDRESS_QUERY =
            "INSERT INTO addresses(street, house_number, postcode) VALUES (?, ?, ?)";

    private static final String UPDATE_ADDRESS_QUERY =
            "UPDATE addresses SET street=?, house_number=?, postcode=? WHERE id = ?";

    private static final String DELETE_ADDRESS_QUERY =
            "DELETE FROM addresses WHERE id = ?";

    ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public Address getEntityById(long addressId) {
        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(READ_ADDRESS_QUERY);
            statement.setLong(1, addressId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String street = rs.getString("street");
                int houseNumber = rs.getInt("house_number");
                String postcode = rs.getString("postcode");

                int id = rs.getInt("id");
                Address address = new Address(street, houseNumber, postcode);
                address.setId(id);
                return address;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateEntity(Address address) {
        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(UPDATE_ADDRESS_QUERY);
            statement.setString(1, address.getStreet());
            statement.setInt(2, address.getHouse_number());
            statement.setString(3, address.getPostcode());
            statement.setLong(4, address.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address createEntity(Address address) {
        try (Connection conn = pool.getConnection()) {

            PreparedStatement statement =
                    conn.prepareStatement(CREATE_ADDRESS_QUERY, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, address.getStreet());
            statement.setInt(2, address.getHouse_number());
            statement.setString(3, address.getPostcode());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                address.setId(resultSet.getInt(1));
            }

            return address;

        } catch (SQLException e) {

            e.printStackTrace();

            return null;

        }
    }

    @Override
    public void removeEntity(long addressId) {
        try (Connection conn = pool.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_ADDRESS_QUERY);
            statement.setLong(1, addressId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
