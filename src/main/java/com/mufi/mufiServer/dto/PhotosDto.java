package com.mufi.mufiServer.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class PhotosDto {
    // 1: 대표사진, 2~10: 일반 사진
    private int photo_number;

    private String payment_id;

    private String image_path;
}
