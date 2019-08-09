package com.practice.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import lombok.Data;

@Data
public class DataTableDto {
	private int draw; 
    private int recordsTotal; 
    private int recordsFiltered; 

    private List<BoardVO> data; 

    public List<BoardVO> getData(){ 
    	if(CollectionUtils.isEmpty(data)){ 
    		data = new ArrayList<>(); 
        } 
        return data; 
    }
}
