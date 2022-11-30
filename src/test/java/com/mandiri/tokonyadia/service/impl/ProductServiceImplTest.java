package com.mandiri.tokonyadia.service.impl;

import com.mandiri.tokonyadia.entity.Product;
import com.mandiri.tokonyadia.repository.ProductRepository;
import com.mandiri.tokonyadia.utils.exception.DataNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private Product product;

    @BeforeEach
    void setUp(){
        product=new Product();
        product.setProductName("Dummmy");
        product.setProductPrice(20000);
        product.setStock(2);
    }
    @Test
    void itShouldProductById(){
        when(productRepository.findById(anyString())).thenReturn(Optional.of(product));
        try {
            Product actualProduct= productService.getProductById(anyString());
            assertEquals(product.getProductName(), actualProduct.getProductName());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    @Test
    void itShouldThrowNotFoundWhenFindByIdIsEmpty(){
        when(productRepository.findById(anyString())).thenReturn(Optional.empty());
        assertThrows(DataNotFoundException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                productService.getProductById(anyString());
            }
        });

    }
}
