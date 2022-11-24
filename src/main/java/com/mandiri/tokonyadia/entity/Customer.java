package com.mandiri.tokonyadia.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="mst_customer")
@Getter
@Setter
@NoArgsConstructor

public class Customer {

    @Id
    @Column(name="customer_id")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy="uuid")
    private String id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String email;
    private String address;
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "UTC")
    private Date birthDate;
    private Integer status;
}


