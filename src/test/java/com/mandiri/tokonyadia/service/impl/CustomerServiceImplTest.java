package com.mandiri.tokonyadia.service.impl;

import com.mandiri.tokonyadia.dto.CustomerSearchDTO;
import com.mandiri.tokonyadia.entity.Customer;
import com.mandiri.tokonyadia.repository.CustomerRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //untuk buat classnya jadi boongan
public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;//jadikan repo boongan

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private Customer customer;

    @BeforeEach
    void setUp(){
        Customer customer = new Customer();
        customer.setFullName("Merin Syafrinka");
        customer.setAddress("Menteng");
        customer.setEmail("merin@gmail.com");
        customer.setBirthDate(new Date());
        customer.setStatus(1);

    }
    @Test
    void itShouldCustomerById() {

        when(customerRepository.findById(anyString())).thenReturn(Optional.of(customer));//memakai when jika ingin memanggil method
        try {
            Customer actualCustomer = customerService.getCustomerById(anyString());
            assertEquals(customer.getFullName(), actualCustomer.getFullName());
        }//actual customer dari cust repo dan ini boongan
        catch (Exception E) {
            throw new RuntimeException();
        }
    }

    @Test
    void itShouldGetAllCustomerList() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer());
        customerList.add(new Customer());
        List<Customer> customer = new ArrayList<>(customerList);

        when(customerRepository.findAll()).thenReturn(customerList);
        try {
            List<Customer> actualCustomer = customerService.getAllCustomer();
            verify(customerRepository, times(1)).findAll();//verifikasi untuk cek custgetall dipanggil 1x atau 2x di times.
            assertEquals(customer.size(), actualCustomer.size());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void itShouldSaveCustomer() {
//        Customer customer = new Customer();
//        customer.setFullName("Merin Syafrinka");
//        customer.setAddress("Menteng");
//        customer.setEmail("merin@gmail.com");
//        customer.setBirthDate(new Date());
//        customer.setStatus(1);
        when(customerRepository.save(customer)).thenReturn(customer);
        try {
            Customer actualCustomer = customerService.saveCustomer(customer);
            assertEquals(customer.getFullName(), actualCustomer.getFullName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void itShouldUpdateCustomer() {
//        Customer customer = new Customer();
//        customer.setFullName("Merin Syafrinka");
//        customer.setAddress("Menteng");
//        customer.setEmail("merin@gmail.com");
//        customer.setBirthDate(new Date());
//        customer.setStatus(1);
        when(customerRepository.save(customer)).thenReturn(customer);
        try {
            Customer actualCustomer = customerService.updateCustomer(customer);
            assertEquals(customer.getId(), actualCustomer.getId());
            System.out.println(customer.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void itShouldDeleteCustomerSuccesfully(){
        Customer customer=new Customer();
        customer.setId("jshoqwo1231");
        doNothing().when(customerRepository).deleteById(customer.getId());
        customerService.deleteCustomer(customer.getId());
        verify(customerRepository, times(1)).deleteById(customer.getId());// 1x delet dan tidak ada kembaliannya jd yg dicek brp x deletenya
    }
    @Test
    void itShoulGetAllCourseList(){
        PageRequest pageRequest= PageRequest.of(1,10);
        //declare cust nya
        Page<Customer> customerMock=mock(Page.class);//dummy data cust dan page nya

        when(customerRepository.findAll(isA(Specification.class),isA(Pageable.class)))
                .thenReturn(customerMock);
        Page<Customer> actualCustomerPage=customerService.getCustomerPerPage(pageRequest, new CustomerSearchDTO(null,null,null));
        assertEquals(customerMock.getTotalElements(),actualCustomerPage.getTotalElements());

    }

}


