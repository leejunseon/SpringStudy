package com.practice.service;

import java.util.List;

import com.practice.domain.BoardVO;
import com.practice.domain.PagingDto;

public interface BoardService {

	public List<BoardVO> getList(PagingDto pagingDto);

	public void register(BoardVO board);
	
	public BoardVO get(Long bno);
	
	public boolean modify(BoardVO board);
	
	public boolean remove(Long bno);
		
	public int getTotal();

}
