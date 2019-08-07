package com.practice.mapper;

import java.util.List;

import com.practice.domain.BoardVO;

public interface BoardMapper {
	
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);//정상 삭제 -> 1반환
	
	public int update(BoardVO board);//정상 업뎃 -> 1반환
}
