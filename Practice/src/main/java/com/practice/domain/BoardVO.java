package com.practice.domain;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class BoardVO {
	private Long bno;
	
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	
	private String writer;
	private Date regdate;
	private Date updateDate;
	private int replyCnt;
}
