package com.practice.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Replies {
	//�Խù� ��ü ��� ������ ��� ����Ʈ
	private int replyCnt;
	private List<ReplyVO> list;
}
