package com.mandiri.tokonyadia.controller;

import com.mandiri.tokonyadia.constant.ApiUrlConstant;
import com.mandiri.tokonyadia.constant.ResponseMessage;
import com.mandiri.tokonyadia.dto.CustomerSearchDTO;
import com.mandiri.tokonyadia.entity.Customer;
import com.mandiri.tokonyadia.service.CustomerService;
import com.mandiri.tokonyadia.utils.PageResponseWrapper;
import com.mandiri.tokonyadia.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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
    public ResponseEntity<Response<Customer>> saveCustomer(@RequestBody Customer customer) {// object diganti ke responseentity karena return nya pesan si entity itu.
        String message= ResponseMessage.INSERTED_DATA;
        Response<Customer> response=new Response<>();
        response.setMessage(message);
        response.setData(customerService.saveCustomer(customer));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable String id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping
    public ResponseEntity<Response<Customer>> updateCustomer(@RequestBody Customer customer) {
        String message= ResponseMessage.UPDATED_DATA;
        Response<Customer> response=new Response<>();
        response.setMessage(message);
        response.setData(customerService.updateCustomer(customer));
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Customer>> deleteCustomer(@PathVariable String id) {
        String message= ResponseMessage.DELETED_DATA;
        Response<Customer> response=new Response<>();
        response.setMessage(message);
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().body(response);
//        customerService.deleteCustomer(id);
    }
    @GetMapping
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }


    @GetMapping("/customerpage")
    public PageResponseWrapper<Customer> getCustomers(@RequestParam(name="page", defaultValue = "1") Integer page,
                                                      @RequestParam(name="size", defaultValue = "3") Integer size,
                                                      @RequestParam(name="sortBy", defaultValue ="fullName") String sort,
                                                      @RequestParam(name="direction", defaultValue = "ASC") String direction,
                                                      @RequestParam (name="fullname", required = false)String fullName,
                                                      @RequestParam (name="address",required = false) String address,
                                                      @RequestParam (name="birthDate",required = false) String birthDate){

        Sort sort1=Sort.by(Sort.Direction.fromString(direction), sort);
        Pageable pageable=PageRequest.of(page,size, sort1);
        CustomerSearchDTO customerSearchDTO=new CustomerSearchDTO();
        customerSearchDTO.setCustomerFullName(fullName);
        customerSearchDTO.setCustomerAddress(address);
        customerSearchDTO.setCustomerBirthDate(birthDate);
//        System.out.println(Date.valueOf(birthDate));
        Page<Customer>customers=customerService.getCustomerPerPage(pageable,customerSearchDTO);
        return new PageResponseWrapper<>(customers);
    }
    @GetMapping("/search")
    public List<Customer> searchCustomer(@RequestParam String fullName){
        return customerService.search(fullName);
    }
}
