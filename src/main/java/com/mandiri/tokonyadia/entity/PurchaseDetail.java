package com.mandiri.tokonyadia.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="trx_purchase_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PurchaseDetail {
    @Id
    @Column(name="purchase_detail_id")
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy="uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    private Integer quantity;
    private Integer productTransactionPrice;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="purchase_id")
    @JsonIgnoreProperties("purchaseDetail")
    private Purchase purchase;

}
