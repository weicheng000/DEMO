package com.example.demo.controller;

import com.example.demo.pojo.Emp;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.page.Page;
import com.example.demo.pojo.page.PageResult;
import com.example.demo.service.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/emps")
@RequiredArgsConstructor
public class EmpController {

	private final EmpService es;

	@GetMapping("")
	public Result queryByParam(Emp emp, Page page){
		log.info("EmpService-Search Start, param: {}, {}", emp, page);

		PageResult pr = es.queryDataByParam(emp, page);

		return Result.success(pr);
	}

	@PostMapping("")
	public Result saveByParam(@RequestBody Emp emp){
		log.info("EmpService-Save Start, param: {}", emp);

		Integer affectedRows = es.saveByParam(emp);

		if (affectedRows > 0) return Result.success();
		else return Result.error("No Change.");
	}

	@DeleteMapping("")
	public Result deleteByParam(Integer id){
		log.info("EmpService-Delete Start, param: {}", id);

		Integer affectedRows = es.deleteByParam(id);

		if (affectedRows > 0) return Result.success();
		else return Result.error("Can't find this data in database.");
	}
}
