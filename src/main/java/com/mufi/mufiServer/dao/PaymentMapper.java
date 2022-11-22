package com.mufi.mufiServer.dao;

import com.mufi.mufiServer.dto.PaymentDto;
import com.mufi.mufiServer.dto.PhotosDto;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface PaymentMapper {
    // TODO: getFirstPhoto, getPhotos 메소드는 PhotoMapper에 선언
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
                    many = @Many(select = "getFirstPhotos"))
    })
    ArrayList<PaymentDto> getPhotoFeed(@Param("id") String id);

    // 한 사용자의 대표 사진들을 받아옴 (ArrayList size: 1)
    @Select("SELECT * FROM photos WHERE payment_id = #{payment_id} AND photo_number = 1")
    ArrayList<PhotosDto> getFirstPhotos(@Param("payment_id") String payment_id);

    
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
                    many = @Many(select = "getPhotos"))
    })
    PaymentDto getPaymentPhotos(String payment_id);

    @Select("SELECT * FROM photos WHERE payment_id = #{payment_id}")
    ArrayList<PhotosDto> getPhotos(@Param("payment_id") String payment_id);
}
