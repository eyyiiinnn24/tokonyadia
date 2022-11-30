package com.mandiri.tokonyadia.controller;

import com.mandiri.tokonyadia.constant.ApiUrlConstant;
import com.mandiri.tokonyadia.constant.ResponseMessage;
import com.mandiri.tokonyadia.dto.PurchaseDTO;
import com.mandiri.tokonyadia.entity.Customer;
import com.mandiri.tokonyadia.entity.Product;
import com.mandiri.tokonyadia.entity.Purchase;
import com.mandiri.tokonyadia.entity.PurchaseDetail;
import com.mandiri.tokonyadia.service.PurchaseDetailService;
import com.mandiri.tokonyadia.service.PurchaseService;
import com.mandiri.tokonyadia.utils.Response;
import lombok.Getter;
import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(ApiUrlConstant.PURCHASE_PATH)

public class PurchaseController {
    PurchaseService purchaseService;
    PurchaseDetailService purchaseDetailService;

    public PurchaseController(PurchaseService purchaseService, PurchaseDetailService purchaseDetailService) {
        this.purchaseService = purchaseService;
        this.purchaseDetailService = purchaseDetailService;
    }
    @PostMapping("/detail")
    public ResponseEntity<Response<PurchaseDetail>> savePurchaseDetail(@RequestBody PurchaseDetail purchaseDetail) {
       String message=String.format(ResponseMessage.SAVE_PURCHASE);
       Response<PurchaseDetail> response= new Response<>();
       response.setMessage(message);
       response.setData(purchaseDetailService.savePurchaseDetail(purchaseDetail));

        return ResponseEntity.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                                .body(response);
    }
    @PostMapping
    public Purchase Transaction(@RequestBody Purchase purchase) {
        return purchaseService.createTransaction(purchase);
    }

    @GetMapping("/{id}") // id nya pake id purchase
    public PurchaseDTO getTransactionByid(@PathVariable String id){
        return purchaseService.getTransactionByid(id);
    }
}


