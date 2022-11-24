package com.mandiri.tokonyadia.service.impl;

import com.mandiri.tokonyadia.constant.ResponseMessage;
import com.mandiri.tokonyadia.entity.Product;
import com.mandiri.tokonyadia.repository.ProductRepository;
import com.mandiri.tokonyadia.service.ProductService;
import com.mandiri.tokonyadia.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) { //save(orm) artinya insert into.
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(String productId) {
        if (productRepository.findById(productId).isPresent()) {
            return productRepository.findById(productId).get();
        } else {
//            throw new DataNotFoundException("Tidak ditemukan data dengan id=" + productId + " , silahkan input ulang");
            throw new DataNotFoundException(String.format(ResponseMessage.NOT_FOUND_MESSAGE,
            ResponseMessage.PRODUCT,productId));
        }
    }
    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> saveBulkProduct(List<Product> products){
        return productRepository.saveAll(products);
    }
//    @Override
//    public List<Product> saveBulkProduct(List<Product> products)
    @Override
    public Page<Product> getProductPerPage(Pageable pageable) {
    return productRepository.findAll(pageable);
}
}
