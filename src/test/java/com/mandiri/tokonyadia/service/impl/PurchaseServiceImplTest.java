package com.mandiri.tokonyadia.service.impl;

import com.mandiri.tokonyadia.entity.Customer;
import com.mandiri.tokonyadia.entity.Product;
import com.mandiri.tokonyadia.entity.Purchase;
import com.mandiri.tokonyadia.entity.PurchaseDetail;
import com.mandiri.tokonyadia.repository.PurchaseRepository;
import com.mandiri.tokonyadia.service.CustomerService;
import com.mandiri.tokonyadia.service.ProductService;
import com.mandiri.tokonyadia.service.PurchaseDetailService;
import com.mandiri.tokonyadia.service.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceImplTest {

    @Mock
    private PurchaseService purchaseService;

    @Mock
    private PurchaseDetailService purchaseDetailService;
    @Mock
    private PurchaseRepository purchaseRepository;
    @Mock
    private ProductService productService;
    @Mock
    private CustomerService customerService;


    @BeforeEach
    void setUp(){
        purchaseService=new PurchaseServiceImpl(purchaseDetailService,purchaseRepository,productService,customerService);

    }
    @Test
    void testTransactionAndShouldReturnTransaction(){
        List<PurchaseDetail> purchaseDetails=new ArrayList<>();
        Purchase purchase=new Purchase();
        purchase.setId("1");
        purchase.setPurchaseDate(new Date());
        purchase.setCustomer(mock(Customer.class));
        purchase.setPurchaseDetail(purchaseDetails);

        when(purchaseRepository.save(any())).thenReturn(purchase);
        Product productMock=new Product();
        productMock.setId("1");
        productMock.setProductName("Downy");
        productMock.setProductPrice(20000);
        productMock.setStock(10);

        when(productService.getProductById(any())).thenReturn(productMock);

        PurchaseDetail purchaseDetail= new PurchaseDetail();
        purchaseDetail.setId("1");
        purchaseDetail.setQuantity(5);
        purchaseDetail.setProductTransactionPrice(20000);
        purchaseDetail.setProduct(productMock);
        purchaseDetail.setPurchase(purchase);
        purchaseDetails.add(purchaseDetail);

        when(purchaseDetailService.savePurchaseDetail(any())).thenReturn(purchaseDetail);

        Purchase transaction = purchaseService.createTransaction(purchase);
        verify(customerService, times(1)).getCustomerById(any());
        verify(purchaseRepository, times(1)).save(any());
        verify(productService, times(2)).getProductById(any());
        verify(purchaseDetailService, times(1)).savePurchaseDetail(any());
//        verifyNoInteractions(purchaseRepository, productService,purchaseDetailService);

        assertEquals(purchase.getPurchaseDetail().get(0).getProduct().getStock(), transaction
                .getPurchaseDetail().get(0).getProduct().getStock());

    }
}
