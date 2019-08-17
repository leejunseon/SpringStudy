package com.practice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.practice.domain.BoardVO;
import com.practice.domain.BoardPagingDto;

public interface BoardMapper {
			
	public List<BoardVO> getList(BoardPagingDto BoardPagingDto);
		
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);//정상 삭제 -> 1반환
	
	public int update(BoardVO board);//정상 업뎃 -> 1반환
	
	public int getTotalCount();
	
	public void updateReplyCnt(@Param("bno")Long bno,@Param("amount")int amount);

}
