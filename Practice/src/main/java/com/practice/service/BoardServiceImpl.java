package com.practice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.domain.BoardVO;
import com.practice.domain.BoardPagingDto;
import com.practice.mapper.BoardMapper;
import com.practice.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
	
	private BoardMapper mapper;
	private ReplyMapper replyMapper;

	@Override
	public List<BoardVO> getList(BoardPagingDto BoardPagingDto) {
		// TODO Auto-generated method stub
		log.info("BoardService : getList");
		return mapper.getList(BoardPagingDto);
	}
	
	@Override
	public void register(BoardVO board) {
		// TODO Auto-generated method stub
		log.info("BoardService : register "+board);
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		// TODO Auto-generated method stub
		log.info("BoardService : get "+bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		// TODO Auto-generated method stub
		log.info("BoardService : modify "+board);
		return mapper.update(board)==1;
	}

	@Override
	public boolean remove(Long bno) {
		// TODO Auto-generated method stub
		log.info("BoardService : remove "+bno);
		replyMapper.deleteAll(bno);
		return mapper.delete(bno)==1;
	}

	@Override
	public int getTotal() {
		// TODO Auto-generated method stub
		log.info("BoardService : getTotal");
		return mapper.getTotalCount();
	}
	
}
