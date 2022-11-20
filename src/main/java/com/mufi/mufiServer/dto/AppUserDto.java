package com.mufi.mufiServer.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class AppUserDto {
    // user_id
    // ^[a-zA-Z0-9]{4,20}$
    private String user_id;

    // user_pw
    // ^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$
    private String user_pw;

    private String name;

    // call_number
    // ^[0][1][0][0-9]{8}$
    private String call_number;

    private Date birth;

    // 1: 남자, 2: 여자
    private int gender;
}
