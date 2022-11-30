package com.mandiri.tokonyadia.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mandiri.tokonyadia.constant.ResponseMessage;
import com.mandiri.tokonyadia.entity.Purchase;
import com.mandiri.tokonyadia.entity.PurchaseDetail;
import com.mandiri.tokonyadia.service.PurchaseDetailService;
import com.mandiri.tokonyadia.service.PurchaseService;
import com.mandiri.tokonyadia.utils.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PurchaseControllerTest.class)
public class PurchaseControllerTest {
    @MockBean
    private PurchaseDetailService purchaseDetailService;

    @MockBean
    private PurchaseService purchaseService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private Purchase purchase;

    @Mock
    private PurchaseDetail purchaseDetail;

    @BeforeEach
    void setUp(){
        purchaseDetail =new PurchaseDetail();
        purchaseDetail.setId("1");

        purchase= new Purchase();
        purchase.setId("1");
    }
    @Test
    void itShouldSavePurchaseDetailAndReturnResponseOk() throws Exception{
        Response<PurchaseDetail> pd = new Response<>();
        pd.setMessage(ResponseMessage.SAVE_PURCHASE);
        when(purchaseDetailService.savePurchaseDetail(any())).thenReturn(purchaseDetail);

        mockMvc.perform(post("/purchases/detail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(purchaseDetail)))
                .andExpect(status().is(201))
                .andExpect(content().json(objectMapper.writeValueAsString(pd)));

        verify(purchaseDetailService, times(1)).savePurchaseDetail(any());
    }
}
