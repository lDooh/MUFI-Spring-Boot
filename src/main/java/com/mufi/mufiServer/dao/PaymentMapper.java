package com.mufi.mufiServer.dao;

import com.mufi.mufiServer.dto.PaymentDto;
import com.mufi.mufiServer.dto.PhotosDto;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface PaymentMapper {
    @Select("SELECT *" +
            " FROM payment_history" +
            " WHERE user_id = #{id}")
    @Results(value = {
            @Result(property = "payment_id", column = "payment_id"),
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "product_id", column = "product_id"),
            @Result(property = "shop_id", column = "shop_id"),
            @Result(property = "payment_date", column = "payment_date"),
            @Result(property = "payment_content", column = "payment_content"),
            @Result(property = "photosDtoArrayList", column = "payment_id", javaType = ArrayList.class,
                    many = @Many(select = "com.mufi.mufiServer.dao.PhotoMapper.getFirstPhotos"))
    })
    ArrayList<PaymentDto> getPhotoFeed(@Param("id") String id);
    
    // 사진 묶음(10장) 전송
    @Select("SELECT *" +
            " FROM payment_history" +
            " WHERE payment_id = #{payment_id}")
    @Results(value = {
            @Result(property = "payment_id", column = "payment_id"),
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "product_id", column = "product_id"),
            @Result(property = "shop_id", column = "shop_id"),
            @Result(property = "payment_date", column = "payment_date"),
            @Result(property = "payment_content", column = "payment_content"),
            @Result(property = "photosDtoArrayList", column = "payment_id", javaType = ArrayList.class,
                    many = @Many(select = "com.mufi.mufiServer.dao.PhotoMapper.getPhotos"))
    })
    PaymentDto getPaymentPhotos(String payment_id);
}
