package com.practice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyPagingDto {
	//페이징 정보
	private int pageNum;
	private int amount;
}
