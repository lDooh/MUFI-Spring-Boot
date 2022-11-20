package com.mufi.mufiServer.dao;

import com.mufi.mufiServer.dto.AppUserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;

@Mapper
public interface UserMapper {
//    @Select("SELECT user_id AS userId, user_pw AS userPw, name, call_number AS callNumber, birth, gender FROM app_user WHERE user_id = #{id}")
    @Select("SELECT user_id, user_pw, name, call_number, birth, gender FROM app_user WHERE user_id = #{id}")
    AppUserDto userLogin(@Param("id") String id, String pw);

    @Select("SELECT user_id FROM app_user WHERE user_id = #{id}")
    String userIdInspection(@Param("id") String id);

    @Insert("INSERT INTO app_user VALUES (#{id}, #{pw}, #{name}, #{callNumber}, #{birth}, #{gender}, NULL)")
    int userSignUp(@Param("id") String id, @Param("pw")String pw, @Param("name")String name,
                   @Param("callNumber") String callNumber, @Param("birth") Date birth, @Param("gender") int gender);
}
