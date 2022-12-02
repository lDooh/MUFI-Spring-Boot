package com.mufi.mufiServer.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class ShopDto {
    private String shop_id;

    private String shop_name;

    private String shop_address;

    private double north_latitude;

    private double east_longitude;

    private Time open_time;

    private Time close_time;

    private int number_booth;

    private int number_using_booth;
}
