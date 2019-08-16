package com.practice.service;

import com.practice.domain.Replies;
import com.practice.domain.ReplyPagingDto;
import com.practice.domain.ReplyVO;

public interface ReplyService {
	
	public int register(ReplyVO vo);
	
	public ReplyVO get(Long rno);
	
	public int modify(ReplyVO vo);
	
	public int remove(Long rno);
	
	public Replies getReplies(Long bno, ReplyPagingDto paging);
}
