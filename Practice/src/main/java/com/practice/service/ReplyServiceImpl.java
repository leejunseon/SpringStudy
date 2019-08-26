package com.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.domain.Replies;
import com.practice.domain.ReplyPagingDto;
import com.practice.domain.ReplyVO;
import com.practice.mapper.BoardMapper;
import com.practice.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService{

	@Setter(onMethod_=@Autowired)
	private ReplyMapper mapper;
	
	@Setter(onMethod_=@Autowired)
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		// TODO Auto-generated method stub
		log.info("ReplyService : register "+vo);
		boardMapper.updateReplyCnt(vo.getBno(), 1);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long rno) {
		// TODO Auto-generated method stub
		log.info("ReplyService : get "+rno);
		return mapper.read(rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		// TODO Auto-generated method stub
		log.info("ReplyService : modify "+vo);
		return mapper.update(vo);
	}

	@Transactional
	@Override
	public int remove(Long rno) {
		// TODO Auto-generated method stub
		log.info("ReplyService : remove "+rno);
		ReplyVO vo=mapper.read(rno);
		boardMapper.updateReplyCnt(vo.getBno(), -1);
		return mapper.delete(rno);
	}

	@Override
	public Replies getReplies(Long bno,ReplyPagingDto paging) {
		// TODO Auto-generated method stub
		log.info("ReplyService : getReplies "+bno);
		return new Replies(mapper.getCountByBno(bno),mapper.getReplies(bno,paging));
	}

}
