package service.impl;

import dto.CustomerDTO;
import entity.Customer;
import repository.CustomerRepository;
import service.ICustomerService;

import java.util.List;


public class CustomerService implements ICustomerService {
    private static CustomerRepository customerRepository = new CustomerRepository();


    @Override
    public List<Customer> getAll() {
        List<Customer> customers = customerRepository.getAll();
        return customers;
    }

    @Override
    public void add(Customer customer) {
        customerRepository.add(customer, customer.getIdClass());
    }


    @Override
    public List<CustomerDTO> getAllDTO() {
        return customerRepository.getAllDTO();
    }

    @Override
    public void update(Customer customer) {
        customerRepository.update(customer, customer.getIdClass());

    }

    @Override
    public void delete(int id) {
        customerRepository.delete(id);

    }

    @Override
    public Customer getById(int id) {
        Customer customer = customerRepository.getById(id);
        return customer;
    }
}
