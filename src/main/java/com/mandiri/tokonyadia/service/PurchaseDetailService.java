package com.mandiri.tokonyadia.service;

import com.mandiri.tokonyadia.entity.Purchase;
import com.mandiri.tokonyadia.entity.PurchaseDetail;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PurchaseDetailService  {
    PurchaseDetail savePurchaseDetail (PurchaseDetail purchaseDetail);

    PurchaseDetail getPurchaseDetailById(String purchaseDetailId);
//    Purchase searchPurchaseById(String id);
//    Page<Purchase> getPurchasePerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO);
}

