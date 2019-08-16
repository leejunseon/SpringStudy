package com.practice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyPagingDto {
	private int pageNum;
	private int amount;
}
