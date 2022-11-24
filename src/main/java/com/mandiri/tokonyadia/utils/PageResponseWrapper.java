package com.mandiri.tokonyadia.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
@Getter
@Setter /// ini anotasi lombok yang memudahkan kita  tidak membuat getter setter

public class PageResponseWrapper<T> {
    private List<T> data;
    private Long totalElement;
    private Integer totalPages;
    private Integer page;
    private Integer size;

    public PageResponseWrapper(Page<T> page) {//param T nya itu generic menerima semua tipe data
        this.data = page.getContent();
        this.totalElement = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.page = page.getNumber();
        this.size = page.getSize();
    }
}
