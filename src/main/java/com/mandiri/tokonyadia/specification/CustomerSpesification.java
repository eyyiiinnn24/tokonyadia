package com.mandiri.tokonyadia.specification;

import com.mandiri.tokonyadia.dto.CustomerSearchDTO;
import com.mandiri.tokonyadia.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public class CustomerSpecification {
    public static Specification<Customer> getSpecification(CustomerSearchDTO customerSearchDTO) {
        return (root, query, criteriaBuilder) -> {
            if (customerSearchDTO.getCustomerFullName() != null) {
                Predicate customerFullNamePredicate = criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%"
                        + customerSearchDTO.getCustomerFullName().toLowerCase()+"%");
            }

        }
    }
}
