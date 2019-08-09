package com.practice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.practice.domain.BoardVO;
import com.practice.domain.Criteria;

public interface BoardMapper {
	
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);//���� ���� -> 1��ȯ
	
	public int update(BoardVO board);//���� ���� -> 1��ȯ
	
	public int getTotalCount();

	public List<BoardVO> findData(@Param("start")int start, @Param("length")int length);
}
