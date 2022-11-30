package com.mandiri.tokonyadia.service.impl;

import com.mandiri.tokonyadia.constant.ResponseMessage;
import com.mandiri.tokonyadia.dto.PurchaseDTO;
import com.mandiri.tokonyadia.dto.PurchaseDetailDTO;
import com.mandiri.tokonyadia.entity.Customer;
import com.mandiri.tokonyadia.entity.Product;
import com.mandiri.tokonyadia.entity.Purchase;
import com.mandiri.tokonyadia.entity.PurchaseDetail;
import com.mandiri.tokonyadia.repository.PurchaseRepository;
import com.mandiri.tokonyadia.service.CustomerService;
import com.mandiri.tokonyadia.service.ProductService;
import com.mandiri.tokonyadia.service.PurchaseDetailService;
import com.mandiri.tokonyadia.service.PurchaseService;
import com.mandiri.tokonyadia.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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

        Purchase purchase1= purchaseRepository.save(purchase2);// save data diatas.

        for (PurchaseDetail pd:purchase.getPurchaseDetail()){
            PurchaseDetail purchaseDetail1=new PurchaseDetail();
            Product product=productService.getProductById(pd.getProduct().getId());
            purchaseDetail1.setProduct(product);
            purchaseDetail1.setQuantity(pd.getQuantity());
            purchaseDetail1.setProductTransactionPrice(product.getProductPrice());
            purchaseDetail1.setPurchase(purchase1);
            purchaseDetail.add(purchaseDetail1);
            TransactionStockless(pd.getProduct().getId(),pd.getQuantity());//update stock
            purchaseDetailService.savePurchaseDetail(purchaseDetail1);//save di database
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
    public void TransactionStockless(String productId, Integer quantity){
        Product product1=productService.getProductById(productId);
        if(product1.getStock()<quantity){
            throw new DataNotFoundException(ResponseMessage.LIMIT_STOCK);
        } else{
            product1.setStock(product1.getStock()-quantity);
            System.out.println(product1.getStock());
        }
    }

    @Override
    public PurchaseDTO getTransactionByid(String id) {
        //cari purchase brdsrkn idny->direpo findbyid(validasi id ditmukan
        //get customer name by return purchase
        if (purchaseRepository.findById(id).isPresent()) {
            Purchase purchase = purchaseRepository.findById(id).get();
            PurchaseDTO purchaseDTO=new PurchaseDTO();
            purchaseDTO.setCustomerName(purchase.getCustomer().getFullName());
            purchaseDTO.setPurchaseDate(purchase.getPurchaseDate());
            List<PurchaseDetailDTO> purchaseDetailDTOS=new ArrayList<>();
            Integer temp=0;// untuk menampumh subtotal yg terus looping biar tidak tereplace.

            for (PurchaseDetail pd:purchase.getPurchaseDetail()) {
                PurchaseDetailDTO purchaseDetailDTO=new PurchaseDetailDTO();
                purchaseDetailDTO.setProductName(pd.getProduct().getId());//nampilin data product
                purchaseDetailDTO.setQuantity(pd.getQuantity());
                purchaseDetailDTO.setPriceSell(pd.getProductTransactionPrice());
                Integer subTotal= pd.getQuantity() * pd.getProductTransactionPrice();
                purchaseDetailDTO.setSubTotal(subTotal);
                purchaseDetailDTOS.add(purchaseDetailDTO);// arraylist terisi dengan data yang sudah masuk di purchasedetDTO kedalam dtos.
                //temp= temp+subTotal; sama aja dengan yg bawah
                temp += subTotal;
            } purchaseDTO.setTotal(temp);
            purchaseDTO.setPurchaseDetailDTOS(purchaseDetailDTOS);//untuk set semua datanya
        return purchaseDTO;
        } else throw new NoSuchElementException();

    }
}

// DAN STOCK PRODUCT BERKURANG BERDASARKAN QUANTITY PEMBELIAN,KETIKA STOCK TIDAK CUKUP
//TRANSAKSI GAGAL, VALIDASI TIDAK CUKUP, BALIKAN DI POST MAN MASIH NULL NULL, SILAHKAN DI BALIKAN DATANYA.

