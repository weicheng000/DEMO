package com.example.demo.mapper;

import com.example.demo.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select username, password from member where username = #{username}")
    Admin login(Admin admin);
}
