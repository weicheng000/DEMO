package com.example.demo.pojo.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
	private Integer total;
	private Object result;
	
	public static PageResult quickMake(Integer total, Object result) {
		return new PageResult(total, result);
	}
}
