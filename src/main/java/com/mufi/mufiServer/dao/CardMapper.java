package com.mufi.mufiServer.dao;

import com.mufi.mufiServer.dto.AppCardDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;
import java.util.ArrayList;

@Mapper
public interface CardMapper {
    @Insert("INSERT INTO app_card VALUES (#{cardId}, #{userId}, #{cardNumber}, #{cardExpDate})")
    int registerCard(@Param("cardId") String cardId, @Param("userId") String userId,
                     @Param("cardNumber") String cardNumber, @Param("cardExpDate") Date cardExpDate);

    @Select("Select * FROM app_card WHERE user_id = #{id}")
    ArrayList<AppCardDto> getCards(@Param("id") String id);
}
