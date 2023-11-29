package com.example.demo.pojo.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class Page {
    private Integer page;
    private Integer itemPerPage;
    private String[] sortBy;

    public Page(){
        this.page = 1;
        this.itemPerPage = 10;
        this.sortBy = new String[]{"id"};
    }

    public Page(Integer page, Integer itemPerPage, String[] sortBy){
        this.page = page;
        this.itemPerPage = itemPerPage;
        this.sortBy = sortBy;
    }

    public Integer limit(){
        return this.itemPerPage;
    }

    public Integer offset(){
        return (this.page - 1) * this.itemPerPage;
    }
}
