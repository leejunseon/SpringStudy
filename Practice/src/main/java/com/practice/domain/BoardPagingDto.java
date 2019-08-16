package com.practice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardPagingDto {
    private String start;
    private String length; 
    private String orderNum;
    private String orderDir;
    private String search;
}
