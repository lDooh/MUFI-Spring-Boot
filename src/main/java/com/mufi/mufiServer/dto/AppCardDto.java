package com.mufi.mufiServer.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class AppCardDto {
    private String card_id;

    private String user_id;

    // ^[0-9]{16}$
    private String card_number;

    private Date card_exp_date;
}
