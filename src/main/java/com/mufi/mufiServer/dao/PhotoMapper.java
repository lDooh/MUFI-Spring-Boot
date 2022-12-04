package com.mufi.mufiServer.dao;

import com.mufi.mufiServer.dto.PhotosDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface PhotoMapper {
    // 한 사용자의 대표 사진들을 받아옴 (ArrayList size: 1)
    @Select("SELECT * FROM photos WHERE payment_id = #{payment_id} AND photo_number = 1")
    ArrayList<PhotosDto> getFirstPhotos(@Param("payment_id") String payment_id);

    // 사진 10장
    @Select("SELECT * FROM photos WHERE payment_id = #{payment_id}")
    ArrayList<PhotosDto> getPhotos(@Param("payment_id") String payment_id);

    // 선택된 사진 한 장
    @Select("SELECT * FROM photos WHERE payment_id = #{payment_id} AND photo_number = #{photo_number}")
    PhotosDto getOriginalPhoto(@Param("payment_id") String payment_id, @Param("photo_number") int photo_number);

    @Delete("DELETE FROM photos WHERE payment_id = #{payment_id} AND photo_number = #{photo_number}")
    int deletePhoto(@Param("payment_id") String payment_id, @Param("photo_number") int photo_number);
}
