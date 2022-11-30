package com.mandiri.tokonyadia.service.impl;

import com.mandiri.tokonyadia.entity.Product;
import com.mandiri.tokonyadia.entity.Purchase;
import com.mandiri.tokonyadia.entity.PurchaseDetail;
import com.mandiri.tokonyadia.repository.PurchaseDetailRepository;
import com.mandiri.tokonyadia.service.PurchaseDetailService;
import com.mandiri.tokonyadia.service.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PurchaseDetailImplTest {

    @InjectMocks
    private PurchaseDetailService purchaseDetailService;
    @Mock
    private PurchaseDetailRepository purchaseDetailRepository;
    @Mock
    private PurchaseDetail purchaseDetail;

@BeforeEach
void setUp(){
    purchaseDetail=new PurchaseDetail();
    purchaseDetail.setId("1");
    purchaseDetail.setQuantity(2);
    purchaseDetail.setProductTransactionPrice(20000);
    purchaseDetail.setProduct(mock(Product.class));
    purchaseDetail.setPurchase(mock(Purchase.class));
}
@Test
    void itShouldSavePurchaseDetailAndReturnPurchaseDetail(){
    try{
        when(purchaseDetailRepository.save(any())).thenReturn(purchaseDetail);
        PurchaseDetail actualPurchasDetail=purchaseDetailService.savePurchaseDetail(purchaseDetail);
    verify(purchaseDetailRepository, times(1)).save(any());
    assertEquals(purchaseDetail,actualPurchasDetail);
    }catch (Exception e){
        throw new RuntimeException(e);
    }
    }
}


