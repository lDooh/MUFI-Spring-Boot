package com.mufi.mufiServer.dao;

import com.mufi.mufiServer.dto.ShopDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface ShopMapper {
    @Select("SELECT * FROM shop")
    ArrayList<ShopDto> getAllShop();

    @Select("SELECT * FROM shop WHERE shop_id = #{shop_id}")
    ShopDto getShopInfo(@Param("shop_id") String shop_id);
}
