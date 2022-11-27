package com.mandiri.tokonyadia.service;

import com.mandiri.tokonyadia.dto.CustomerSearchDTO;
import com.mandiri.tokonyadia.entity.Customer;
import com.mandiri.tokonyadia.entity.Product;
import com.mandiri.tokonyadia.entity.Purchase;
import com.mandiri.tokonyadia.entity.PurchaseDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PurchaseService {
    Purchase createTransaction (Purchase purchase);
    public Purchase TransactionStockless(Product product, PurchaseDetail purchaseDetail);


}
