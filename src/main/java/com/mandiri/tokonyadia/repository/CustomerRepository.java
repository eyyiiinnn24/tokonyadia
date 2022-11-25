package com.mandiri.tokonyadia.repository;

import com.mandiri.tokonyadia.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer,String>, JpaSpecificationExecutor<Customer> {
    //boleh menambahkan native query jika jpa tidak mengcover kebutuhan user

    List<Customer> findCustomerByFullNameIsLikeIgnoreCase(String nameCriteria);

}
