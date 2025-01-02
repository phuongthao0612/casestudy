package repository;

import dto.CustomerDTO;
import entity.Customer;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    private static List<Customer> customers = new ArrayList<Customer>();

    public List<Customer> getAll() {

        List<Customer> customers = new ArrayList<>();
        try {
            PreparedStatement statement = BaseRepository.getConnection()
                    .prepareStatement("select * from customers");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                String name = resultSet.getString("customer_name");
                int age = resultSet.getInt("age");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
        //        customers.add(new Customer(id, name, age, phone, email));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customers;
    }

    public List<CustomerDTO> getAllDTO() {
        List<CustomerDTO> customerDTO = new ArrayList<>();
        String query = "SELECT c.customer_id, c.customer_name, c.age, c.phone, c.email, g.class_name " +
                "FROM customers c " +
                "LEFT JOIN enrollments e ON c.customer_id = e.customer_id " +
                "LEFT JOIN gym_classes g ON e.class_id = g.class_id";
        try (PreparedStatement statement = BaseRepository.getConnection().prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("customer_id");
                String name = resultSet.getString("customer_name");
                int age = resultSet.getInt("age");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String className = resultSet.getString("class_name");
                customerDTO.add(new CustomerDTO(id, name, age, phone, email, className));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerDTO;
    }

    public void add(Customer customer, int classId) {
        String sqlCustomer = "INSERT INTO customers (customer_name, age, phone, email) VALUES (?, ?, ?, ?)";
        String sqlEnrollment = "INSERT INTO enrollments (customer_id, class_id, registration_date) VALUES (LAST_INSERT_ID(), ?, ?)";

        try (PreparedStatement statement = BaseRepository.getConnection().prepareStatement(sqlCustomer)) {
            statement.setString(1, customer.getName());
            statement.setInt(2, customer.getAge());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getEmail());
            statement.executeUpdate();
            try (PreparedStatement enrollmentStatement = BaseRepository.getConnection().prepareStatement(sqlEnrollment)) {
                enrollmentStatement.setInt(1, classId);
                enrollmentStatement.setDate(2, Date.valueOf(java.time.LocalDate.now()));
                enrollmentStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Customer customer, int newClassId) {
        String updateCustomerSQL = "UPDATE customers SET customer_name = ?, age = ?, phone = ?, email = ? WHERE customer_id = ?";
        String updateEnrollmentSQL = "UPDATE enrollments SET class_id = ? WHERE customer_id = ?";

        try (PreparedStatement statement = BaseRepository.getConnection().prepareStatement(updateCustomerSQL)) {
            statement.setString(1, customer.getName());
            statement.setInt(2, customer.getAge());
            statement.setString(3, customer.getPhone());
            statement.setString(4, customer.getEmail());
            statement.setInt(5, customer.getId());
            statement.executeUpdate();
            if (newClassId != -1) {
                try (PreparedStatement enrollmentStatement = BaseRepository.getConnection().prepareStatement(updateEnrollmentSQL)) {
                    enrollmentStatement.setInt(1, newClassId);
                    enrollmentStatement.setInt(2, customer.getId());
                    enrollmentStatement.executeUpdate();
                }
            }
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
                Integer idClass = null;
                customer = new Customer(id, name, age, phone, email, idClass);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }
}

