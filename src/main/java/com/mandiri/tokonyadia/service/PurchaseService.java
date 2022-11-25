package com.mandiri.tokonyadia.service;

import com.mandiri.tokonyadia.dto.CustomerSearchDTO;
import com.mandiri.tokonyadia.entity.Customer;
import com.mandiri.tokonyadia.entity.Product;
import com.mandiri.tokonyadia.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PurchaseService {
    Purchase savePurchase (Purchase purchase);
//    Purchase updatePurchase(Purchase purchase);
    List<Purchase> getAllPurchase();
    Purchase getPurchaseById(String purchaseId);
    Purchase searchPurchaseById(String id);
    Page<Purchase> getPurchasePerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO);
}
