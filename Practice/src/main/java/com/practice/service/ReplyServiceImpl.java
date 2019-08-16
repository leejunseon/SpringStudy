package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.domain.Replies;
import com.practice.domain.ReplyPagingDto;
import com.practice.domain.ReplyVO;
import com.practice.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService{

	@Setter(onMethod_=@Autowired)
	private ReplyMapper mapper;
	
	@Override
	public int register(ReplyVO vo) {
		// TODO Auto-generated method stub
		log.info("register:"+vo);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		// TODO Auto-generated method stub
		log.info("get:"+rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		// TODO Auto-generated method stub
		log.info("modify:"+vo);
		return mapper.update(vo);
	}

	@Override
	public int remove(Long rno) {
		// TODO Auto-generated method stub
		log.info("remove:"+rno);
		return mapper.delete(rno);
	}

	@Override
	public Replies getReplies(Long bno,ReplyPagingDto paging) {
		// TODO Auto-generated method stub
		log.info("get Replies:"+paging);
		return new Replies(mapper.getCountByBno(bno),mapper.getReplies(bno,paging));
	}

}
