package com.mandiri.tokonyadia.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mandiri.tokonyadia.constant.ResponseMessage;
import com.mandiri.tokonyadia.controller.CustomerController;
import com.mandiri.tokonyadia.entity.Customer;
import com.mandiri.tokonyadia.service.CustomerService;
import com.mandiri.tokonyadia.utils.PageResponseWrapper;
import com.mandiri.tokonyadia.utils.Response;
import org.hibernate.SQLQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    private CustomerService customerService;

    @Autowired
    private MockMvc mockMvc; //dibutuhkan ketika mau test controllernya

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    Customer customer;

    @BeforeEach
    void setUp(){
        customer=new Customer();
        customer.setId("11242");
        customer.setFullName("Merin Syafrinka");
        customer.setAddress("Menteng");
        customer.setEmail("merin@gamil.com");
        customer.setBirthDate(new Date());
        customer.setStatus(0);

    }
    @Test
    void itShouldCreateCustomerAndReturnCustomerResponseOfCustomerAndStatusOk() throws Exception {
        when(customerService.saveCustomer(any())).thenReturn(customer);

        Response<Customer> response=new Response<>(
                String.format(ResponseMessage.INSERTED_DATA),customer);
        mockMvc.perform(post("/customers")//mockmvc cek url dengan expected
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));

    }
    @Test
    void itShouldUpdateCustomerAndStatusOk() throws Exception{
        when(customerService.updateCustomer(any())).thenReturn(customer);
        Response<Customer> response=new Response<>(
                String.format(ResponseMessage.UPDATED_DATA),customer);
        mockMvc.perform(put("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }
    @Test
    void itShouldThrowsResponseStatusBadRequestWhenUpdatedCustomerFailed() throws Exception{
        when(customerService.updateCustomer(any())).thenReturn(null);//null karena ingin digagalkan prosesnya
        mockMvc.perform(put("/customers")).andExpect(status().isBadRequest());//gaada return krena kalo bad request tidak ada balikan datanya.
    }

    @Test
    void itsShouldDeleteCustomerSuccesfullyOk() throws Exception{
       doNothing().when(customerService).deleteCustomer(any());//do nothing digunkan ketika tidak ada return
               mockMvc.perform(delete("/customers/1")).andExpect(status().isOk());

               verify(customerService, times(1)).deleteCustomer(any());
    }

    @Test
    void itShouldGetAllCustomerByRequestParamAndStatusOk() throws Exception{
        Page<Customer> customers=new PageImpl<>(Arrays.asList(customer));
        PageResponseWrapper<Customer> customerPageResponseWrapper=new PageResponseWrapper<>(customers);

        when(customerService.getCustomerPerPage(isA(Pageable.class),any()))//isa digunakan untuk array
                .thenReturn(customers);

        mockMvc.perform(get("/customers/customerpage?fullName=titan")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(customerPageResponseWrapper)));
    }

    @Test
    void searchCustomerWillReturnListOfCustomerAndStatusOk() throws Exception{
        List<Customer> customers=Arrays.asList(
                new Customer(),
                new Customer()
        );
        when(customerService.search(any())).thenReturn(customers);
        mockMvc.perform(get("/customers/search?fullName=doni")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(customers)));

        verify(customerService, times(1)).search(any());
    }

}
