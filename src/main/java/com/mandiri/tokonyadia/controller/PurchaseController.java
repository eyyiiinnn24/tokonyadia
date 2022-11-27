package com.mandiri.tokonyadia.controller;

import com.mandiri.tokonyadia.constant.ApiUrlConstant;
import com.mandiri.tokonyadia.constant.ResponseMessage;
import com.mandiri.tokonyadia.entity.Customer;
import com.mandiri.tokonyadia.entity.Product;
import com.mandiri.tokonyadia.entity.Purchase;
import com.mandiri.tokonyadia.entity.PurchaseDetail;
import com.mandiri.tokonyadia.service.PurchaseService;
import com.mandiri.tokonyadia.utils.Response;
import net.bytebuddy.asm.Advice;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrlConstant.PURCHASE_PATH)

public class PurchaseController {
    PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping
    public Purchase Transaction(@RequestBody Purchase purchase) {
        return purchaseService.createTransaction(purchase);
    }

    @PostMapping("/stock")
    public Purchase TransactionStockless(@RequestBody Product product, PurchaseDetail purchaseDetail){
        return purchaseService.TransactionStockless(product, purchaseDetail);
    }
}


