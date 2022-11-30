package com.mandiri.tokonyadia.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseDetailDTO {
    private String productName;
    private Integer quantity;
    private Integer priceSell;//product trans price detail//  untuk tau jikalau harga pembayrannya berbeda dengan update harga db
    private Integer subTotal;//total per barang/per purchasedetail
}
