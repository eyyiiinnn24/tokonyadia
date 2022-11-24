package com.mandiri.tokonyadia.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="mst_product")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @Column(name="product_id")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy="uuid")
    private String id;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private Integer productPrice;
    @Column(nullable = false)
    private Integer stock;

}
