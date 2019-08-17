package com.practice.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	//´ñ±Û °´Ã¼
	private Long rno;
	private Long bno;
	
	private String reply;
	private String replyer;
	private Date replyDate;
	private Date updateDate;
}
