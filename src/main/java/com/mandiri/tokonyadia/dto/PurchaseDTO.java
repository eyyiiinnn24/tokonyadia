package com.mandiri.tokonyadia.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter

public class PurchaseDTO {
    private String customerName;
    private Integer total;//jumlah total 1x transaksi, semua barang
    private Date purchaseDate;
    private List<PurchaseDetailDTO>purchaseDetailDTOS;//untuk mendapatkan daftar transaksi

}
