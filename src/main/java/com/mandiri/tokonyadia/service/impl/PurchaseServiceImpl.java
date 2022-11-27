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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    PurchaseRepository purchaseRepository;
    PurchaseDetailService purchaseDetailService;
    ProductService productService;
    CustomerService customerService;


    @Autowired
    public PurchaseServiceImpl(PurchaseDetailService purchaseDetailService, PurchaseRepository purchaseRepository,ProductService productService, CustomerService customerService) {
        this.purchaseDetailService = purchaseDetailService;
        this.purchaseRepository=purchaseRepository;
        this.productService=productService;
        this.customerService=customerService;
    }
    @Override
    @Transactional // begin commit rollback
    public Purchase createTransaction(Purchase purchase) {
        Purchase purchase2=new Purchase();
        Customer customer= customerService.getCustomerById(purchase.getCustomer().getId());
        purchase2.setCustomer(customer);//set data customer
        List<PurchaseDetail> purchaseDetail=new ArrayList<>();//nampung data purchase detail
        PurchaseDetail purchaseDetail1=new PurchaseDetail();
        Purchase purchase1= purchaseRepository.save(purchase2);// save data diatas.

        for (PurchaseDetail pd:purchase.getPurchaseDetail()){
            Product product=productService.getProductById(pd.getProduct().getId());
            purchaseDetail1.setProduct(product);
            purchaseDetail1.setQuantity(pd.getQuantity());
            purchaseDetail1.setProductTransactionPrice(product.getProductPrice());
            purchaseDetail1.setPurchase(purchase1);
            purchaseDetail.add(purchaseDetail1);
            purchaseDetailService.savePurchaseDetail(purchaseDetail1);
        }
        purchase1.setPurchaseDetail(purchaseDetail);
        return purchase1;
    }
//    public Purchase createTransactionAutoDatePrice(Purchase purchase){
//        Purchase purchase2=new Purchase();
//        Purchase savePurchase2= purchaseRepository.save(purchase);
//        for (PurchaseDetail pd:purchase.getPurchaseDetail()){
//            String productId=pd.getProduct().getId();
//            Product product= productService.getProductById(productId);
//            Integer productPrice= product.getProductPrice();
//            pd.setProductTransactionPrice(productPrice);
//            pd.setPurchase(purchase2);
//            purchaseDetailService.savePurchaseDetail(pd);
//        }
//        return savePurchase2;
//    }
    public Purchase TransactionStockless(Product product,PurchaseDetail purchaseDetail){
        Product product1=new Product();
        PurchaseDetail purchaseDetail1=new PurchaseDetail();
        if(product1.getStock()<purchaseDetail1.getQuantity()){
            System.out.println("NOT ENOUGH STOCK!");
        } else if (product1.getStock()>purchaseDetail1.getQuantity()) {
            System.out.println(product1.getStock()- purchaseDetail.getQuantity());
        }return null;
    }
}

// DAN STOCK PRODUCT BERKURANG BERDASARKAN QUANTITY PEMBELIAN,KETIKA STOCK TIDAK CUKUP
//TRANSAKSI GAGAL, VALIDASI TIDAK CUKUP, BALIKAN DI POST MAN MASIH NULL NULL, SILAHKAN DI BALIKAN DATANYA.

