package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.pojo.Emp;

@Mapper
public interface EmpMapper {

	Integer getDataTotal(Emp emp);

	List<Emp> getDataByPage(Integer limit, Integer offset);

	List<Emp> queryDataByParam(Emp emp, Integer limit, Integer offset);

	Integer updateByParam(Emp emp);

	Integer insertByParam(Emp emp);

	Integer deleteById(Integer id);
}
