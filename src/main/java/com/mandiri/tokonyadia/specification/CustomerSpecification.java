package com.mandiri.tokonyadia.specification;

import com.mandiri.tokonyadia.dto.CustomerSearchDTO;
import com.mandiri.tokonyadia.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CustomerSpecification { //ini criteria builder
    public static Specification<Customer> getSpecification(CustomerSearchDTO customerSearchDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (customerSearchDTO.getCustomerFullName() != null) {
                Predicate customerFullNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%"
                        + customerSearchDTO.getCustomerFullName().toLowerCase() + "%");
                predicates.add(customerFullNamePredicate);
            }
            if (customerSearchDTO.getCustomerAddress() != null) {

                Predicate customerAddressPredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("address")),
                        "%" + customerSearchDTO.getCustomerAddress().toLowerCase() + "%");
                predicates.add(customerAddressPredicate);
            }
            if (customerSearchDTO.getCustomerBirthDate() != null) {
                Predicate customerBirthDatePredicate = criteriaBuilder.equal(root.get("birthDate"), Date.valueOf(customerSearchDTO.getCustomerBirthDate()));
                predicates.add(customerBirthDatePredicate);
            }
            Predicate[] arrpredicate = predicates.toArray(new Predicate[predicates.size()]);
            return criteriaBuilder.and(arrpredicate);
        };
    }
}

