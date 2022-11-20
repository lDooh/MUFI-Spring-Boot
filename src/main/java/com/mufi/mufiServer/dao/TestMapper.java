package com.mufi.mufiServer.dao;

import com.mufi.mufiServer.dto.TestUserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface TestMapper {
    @Select("SELECT name FROM test WHERE id = #{id}")
    String getUserName(@Param("id") String id);

    @Insert("INSERT INTO test VALUES(#{id}, #{name})")
    int addUser(@Param("id")String id, @Param("name")String name);

    @Select("SELECT * FROM test")
    ArrayList<TestUserDto> getAllUser();
}
