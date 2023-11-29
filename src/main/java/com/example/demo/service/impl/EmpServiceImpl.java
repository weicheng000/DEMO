package com.example.demo.service.impl;

import com.example.demo.mapper.EmpMapper;
import com.example.demo.pojo.Emp;
import com.example.demo.pojo.page.Page;
import com.example.demo.pojo.page.PageResult;
import com.example.demo.service.EmpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {

    private final EmpMapper em;

    @Override
    public PageResult getDataByPage(Emp emp,Page page) {

        Integer total = em.getDataTotal(emp);

        log.info("start page service, param: {}, {}", page.limit(), page.offset());
        List<Emp> emps = em.getDataByPage(page.limit(), page.offset());

        return new PageResult(total, emps);
    }

    @Override
    public PageResult queryDataByParam(Emp emp, Page page) {

        Integer total = em.getDataTotal(emp);

        List<Emp> emps = em.queryDataByParam(emp, page.limit(), page.offset());

        return new PageResult(total, emps);
    }

    @Override
    public Integer saveByParam(Emp emp) {
        if (emp.getId() != null) return em.updateByParam(emp);
        else return em.insertByParam(emp);
    }

    @Override
    public Integer deleteByParam(Integer id) {
        if (id != null && id != 0) return em.deleteById(id);
        else return 0;
    }
}
