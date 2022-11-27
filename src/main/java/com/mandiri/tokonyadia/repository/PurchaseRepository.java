package com.mandiri.tokonyadia.repository;

import com.mandiri.tokonyadia.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, String>{


}
