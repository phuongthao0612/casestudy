package repository;

import entity.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static List<Customer> customers = new ArrayList<Customer>();

    public List<Customer> getAll() {
        PreparedStatement statement = null;
        List<Customer> customers = new ArrayList<>();
        try {
            statement = BaseRepository.getConnection()
                    .prepareStatement("select * from customers");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                String name = resultSet.getString("customer_name");
                int age = resultSet.getInt("age");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                customers.add(new Customer(id, name, age, phone, email));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    public void add(Customer customer) {
        String sql = "INSERT INTO customers (customer_name, age, phone, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = BaseRepository.getConnection().prepareStatement(sql)) {
            statement.setString(1, customer.getName());
            statement.setInt(2, customer.getAge());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Customer customer) {
        String sql = "UPDATE customers SET customer_name = ?, age = ?, phone = ?, email = ? WHERE id = ?";
        try (PreparedStatement statement = BaseRepository.getConnection().prepareStatement(sql)) {
            statement.setString(1, customer.getName());
            statement.setInt(2, customer.getAge());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getEmail());
            statement.setInt(5, customer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM customers WHERE id = ?";
        try (PreparedStatement statement = BaseRepository.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Customer getById(int id) {
        String sql = "SELECT * FROM customers WHERE id = ?";
        try (PreparedStatement statement = BaseRepository.getConnection().prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("customer_name");
                int age = resultSet.getInt("age");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                return new Customer(id, name, age, phone, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

