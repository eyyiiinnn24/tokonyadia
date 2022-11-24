package com.mandiri.tokonyadia.controller;

import com.mandiri.tokonyadia.constant.ApiUrlConstant;
import com.mandiri.tokonyadia.entity.Customer;
import com.mandiri.tokonyadia.entity.Product;
import com.mandiri.tokonyadia.service.ProductService;
import com.mandiri.tokonyadia.utils.PageResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT_PATH)
public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PostMapping("/bulkproducts")
    public List<Product> saveBulkProduct(@RequestBody List<Product> products)
    {
        return productService.saveBulkProduct(products);
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }
    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
    @GetMapping
    public List<Product>getAllProduct(){
        return productService.getAllProduct();
    }
    @GetMapping("/productpage")
    public PageResponseWrapper<Product> getProducts(@RequestParam(name="page", defaultValue = "1") Integer page,
                                                    @RequestParam(name="size", defaultValue = "3") Integer size){

        Pageable pageable= PageRequest.of(page,size);
        Page<Product> products=productService.getProductPerPage(pageable);
        return new PageResponseWrapper<>(products);
    }
}
