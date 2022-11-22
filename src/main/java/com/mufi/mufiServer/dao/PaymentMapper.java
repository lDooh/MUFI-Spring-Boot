package com.mufi.mufiServer.dao;

import com.mufi.mufiServer.dto.PaymentDto;
import lombok.Setter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface PaymentMapper {
    @Select("SELECT *" +
            " FROM payment_history JOIN photos ON payment_history.payment_id = photos.payment_id" +
            " WHERE payment_history.user_id = #{id} AND photos.photo_number = 1")
    ArrayList<PaymentDto> getPhotoFeed(@Param("id") String id);
}
