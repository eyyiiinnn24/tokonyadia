package com.mandiri.tokonyadia.entity;
//materi many to one and reverse r in here, entity transaksi.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="trx_purchase")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Purchase {
    @Id
    @Column(name="purchase_id")
    @GeneratedValue(generator ="system-uuid")
    @GenericGenerator(name="system-uuid", strategy="uuid")
    private String id;

    private Date purchaseDate;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @OneToMany (mappedBy = "purchase")
    private List<PurchaseDetail> purchaseDetail=new ArrayList<>();
}
