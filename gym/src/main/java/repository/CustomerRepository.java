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
        try (PreparedStatement statement = BaseRepository.getConnection().
                prepareStatement("insert into customers (customer_name, age, phone, email) values (?, ?, ?, ?)")) {
            statement.setString(1, customer.getName());
            statement.setInt(2, customer.getAge());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Customer customer) {
        try (PreparedStatement statement = BaseRepository.getConnection().
                prepareStatement("update customers set customer_name = ?, age = ?, phone = ?, email = ? where customer_id = ?")) {
            statement.setString(1, customer.getName());
            statement.setInt(2, customer.getAge());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getEmail());
            statement.setInt(5, customer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id) {
        try (PreparedStatement statement = BaseRepository.getConnection().
                prepareStatement("delete from customers where customer_id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer getById(int id) {
        Customer customer = null;
        try (PreparedStatement statement = BaseRepository.getConnection().
                prepareStatement("select * from customers where customer_id = ?")) {
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
            throw new RuntimeException(e);
        }
        return null;
    }
}

