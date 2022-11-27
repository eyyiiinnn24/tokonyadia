package com.mandiri.tokonyadia.repository;

import com.mandiri.tokonyadia.entity.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDetailRepository extends JpaRepository <PurchaseDetail, String> {

}
