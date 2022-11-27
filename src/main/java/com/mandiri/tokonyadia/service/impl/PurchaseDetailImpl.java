package com.mandiri.tokonyadia.service.impl;

import com.mandiri.tokonyadia.constant.ResponseMessage;
import com.mandiri.tokonyadia.entity.PurchaseDetail;
import com.mandiri.tokonyadia.repository.PurchaseDetailRepository;
import com.mandiri.tokonyadia.service.PurchaseDetailService;
import com.mandiri.tokonyadia.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailImpl implements PurchaseDetailService {

    PurchaseDetailRepository purchaseDetailRepository;

    @Autowired
    public PurchaseDetailImpl(PurchaseDetailRepository purchaseDetailRepository) {
        this.purchaseDetailRepository = purchaseDetailRepository;
    }

    @Override
    public PurchaseDetail savePurchaseDetail(PurchaseDetail purchaseDetail) {
        return purchaseDetailRepository.save(purchaseDetail);
    }

    @Override
    public PurchaseDetail getPurchaseDetailById(String purchaseDetailId) {
            if (purchaseDetailRepository.findById(purchaseDetailId).isPresent()) {
                return purchaseDetailRepository.findById(purchaseDetailId).get();
            } else {
                throw new DataNotFoundException(String.format(ResponseMessage.NOT_FOUND_MESSAGE,
                        ResponseMessage.PRODUCT,purchaseDetailId));
            }
        }

//
//    @Override
//    public Purchase searchPurchaseById(String id) {
//        return null;
//    }
}
