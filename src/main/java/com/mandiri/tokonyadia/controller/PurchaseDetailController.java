package com.mandiri.tokonyadia.controller;

import com.mandiri.tokonyadia.constant.ApiUrlConstant;
import com.mandiri.tokonyadia.entity.Purchase;
import com.mandiri.tokonyadia.entity.PurchaseDetail;
import com.mandiri.tokonyadia.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrlConstant.PURCHASEDETAIL_PATH)
public class PurchaseDetailController {
    PurchaseDetailService purchaseDetailService;

    @Autowired
    public PurchaseDetailController(PurchaseDetailService purchaseDetailService) {
        this.purchaseDetailService = purchaseDetailService;
    }
    @PostMapping
    public PurchaseDetail savePurchaseDetail(@RequestBody PurchaseDetail purchaseDetail) {
        return purchaseDetailService.savePurchaseDetail(purchaseDetail);
    }
    public PurchaseDetail getPurchaseDetailById(@RequestBody String purchaseDetailId){
        return null;

    }
}
