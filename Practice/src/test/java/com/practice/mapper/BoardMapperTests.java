package com.practice.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.practice.domain.BoardVO;
import com.practice.domain.PagingDto;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;
	
	/*@Test
	public void testGetList() {
		PagingDto pDto=new PagingDto("0","10","1","asc",null);
		mapper.getList(pDto).forEach(board->log.info(board));
	}*/
		
	/*@Test
	public void testInsertSelectKey() {
		BoardVO board=new BoardVO();
		board.setTitle("���� �ۼ��ϴ� �� select key");
		board.setContent("���� �ۼ��ϴ� �� select key");
		board.setWriter("newbie");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
	}*/
	
	/*@Test
	public void testRead() {
		BoardVO board=mapper.read(5L);
		log.info(board);
	}*/
	
	/*@Test
	public void testDelete() {
		log.info("DELETE COUNT: "+mapper.delete(235234L));
	}*/
	
	/*@Test
	public void testUpdate() {
		BoardVO board=new BoardVO();
		board.setBno(393329L);
		board.setTitle("������ ����");
		board.setContent("������ ����");
		board.setWriter("user00");
		int count=mapper.update(board);
		log.info("UPDATE COUNT: "+count);
	}*/
	
	@Test
	public void testGetTotal() {
		int total=mapper.getTotalCount();
		log.info("total: "+total);
	}
	
}
