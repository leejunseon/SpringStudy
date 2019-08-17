package com.practice.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Replies {
	//게시물 전체 댓글 갯수와 댓글 리스트
	private int replyCnt;
	private List<ReplyVO> list;
}
