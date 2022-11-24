package com.mandiri.tokonyadia.controller;

import com.mandiri.tokonyadia.constant.ApiUrlConstant;
import com.mandiri.tokonyadia.entity.Customer;
import com.mandiri.tokonyadia.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.CUSTOMER_PATH)
public class CustomerController {
    CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }
    @GetMapping
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }


    @GetMapping("/customerpage")
    public Page<Customer> getCustomers(@RequestParam(name="page", defaultValue = "1") Integer page,
                                       @RequestParam(name="size", defaultValue = "3") Integer size){

        Pageable pageable=PageRequest.of(page,size);
        return customerService.getCustomerPerPage(pageable);
    }
}
