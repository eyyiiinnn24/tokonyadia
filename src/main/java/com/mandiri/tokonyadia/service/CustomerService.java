package com.mandiri.tokonyadia.service;

import com.mandiri.tokonyadia.dto.CustomerSearchDTO;
import com.mandiri.tokonyadia.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

        Customer saveCustomer(Customer customer);
        Customer updateCustomer(Customer customer);
        List<Customer> getAllCustomer();
        Customer getCustomerById(String CustomerId);
        Customer deleteCustomer(String id);

        Page<Customer> getCustomerPerPage(Pageable pageable, CustomerSearchDTO  customerSearchDTO);

        public List<Customer> search(String criteriaName);

    }

