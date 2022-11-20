package com.mufi.mufiServer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String product_id;

    private String shop_id;

    private String product_name;

    private int product_price;

    private int product_number;
}
