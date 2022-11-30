package com.mandiri.tokonyadia.service.impl;
import com.mandiri.tokonyadia.dto.CustomerSearchDTO;
import com.mandiri.tokonyadia.entity.Customer;
import com.mandiri.tokonyadia.repository.CustomerRepository;
import com.mandiri.tokonyadia.service.CustomerService;
import com.mandiri.tokonyadia.specification.CustomerSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
    @Override
    public Page<Customer> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO){
        Specification<Customer> customerSpecification = CustomerSpecification.getSpecification(customerSearchDTO);
        return customerRepository.findAll(customerSpecification, pageable);
    }
    @Override
    public List<Customer> search(String criteriaName) {
        return customerRepository.findCustomerByFullNameIsLikeIgnoreCase(criteriaName);
        //kalo mau mencari bagian salah satu nama maka gunakan IsContaining pada nama method.
    }
}
