package com.practice.service;

import java.util.List;

import com.practice.domain.BoardVO;
import com.practice.domain.PagingDto;

public interface BoardService {

	public void register(BoardVO board);
	
	public BoardVO get(Long bno);
	
	public boolean modify(BoardVO board);
	
	public boolean remove(Long bno);
	
	public List<BoardVO> getList();//페이징 정보 파라미터
	
	public int getTotal();

	public List<BoardVO> findData(PagingDto pagingDto);
}
