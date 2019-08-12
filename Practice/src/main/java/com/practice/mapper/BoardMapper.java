package com.practice.mapper;

import java.util.List;

import com.practice.domain.BoardVO;
import com.practice.domain.PagingDto;

public interface BoardMapper {
			
	public List<BoardVO> getList(PagingDto pagingDto);
		
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);//���� ���� -> 1��ȯ
	
	public int update(BoardVO board);//���� ���� -> 1��ȯ
	
	public int getTotalCount();

}
