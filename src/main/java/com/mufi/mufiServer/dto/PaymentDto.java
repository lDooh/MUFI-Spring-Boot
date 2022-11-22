package com.mufi.mufiServer.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;

@Getter
@Setter
public class PaymentDto {
    private String payment_id;

    private String user_id;

    private String product_id;

    private String shop_id;

    private Date payment_date;

    private String payment_content;

    private ArrayList<PhotosDto> photosDtoArrayList = new ArrayList<>();
}
