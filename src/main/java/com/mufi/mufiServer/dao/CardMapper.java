package com.mufi.mufiServer.dao;

import com.mufi.mufiServer.dto.AppCardDto;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.ArrayList;

@Mapper
public interface CardMapper {
    @Insert("INSERT INTO app_card VALUES (#{cardId}, #{userId}, #{cardNumber}, #{cardExpDate})")
    int registerCard(@Param("cardId") String cardId, @Param("userId") String userId,
                     @Param("cardNumber") String cardNumber, @Param("cardExpDate") Date cardExpDate);

    @Select("Select * FROM app_card WHERE user_id = #{id}")
    ArrayList<AppCardDto> getCards(@Param("id") String id);

    @Delete("DELETE FROM app_card WHERE card_id = #{card_id}")
    boolean deleteCard(@Param("card_id") String card_id);
}
