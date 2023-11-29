package com.example.demo.service;

import com.example.demo.pojo.Emp;
import com.example.demo.pojo.page.Page;
import com.example.demo.pojo.page.PageResult;

public interface EmpService {
    PageResult getDataByPage(Emp emp, Page page);

    PageResult queryDataByParam(Emp emp, Page page);

    Integer saveByParam(Emp emp);

    Integer deleteByParam(Integer id);
}
