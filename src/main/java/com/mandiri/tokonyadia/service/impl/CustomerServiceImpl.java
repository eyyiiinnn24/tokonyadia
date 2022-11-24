package com.mandiri.tokonyadia.service.impl;
import com.mandiri.tokonyadia.entity.Customer;
import com.mandiri.tokonyadia.repository.CustomerRepository;
import com.mandiri.tokonyadia.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(String CustomerId) {
        return customerRepository.findById(CustomerId).get();
    }
    @Override
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
