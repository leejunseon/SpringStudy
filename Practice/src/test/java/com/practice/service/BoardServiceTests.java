package com.practice.service;

import static org.junit.Assert.assertNotNull;

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
public class BoardServiceTests {
	
	@Setter(onMethod_= {@Autowired})
	private BoardService service;
	
	/*@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}*/
	
	/*@Test
	public void testGetList() {
		service.getList(new PagingDto("0","10","1","asc",null)).forEach(board->log.info(board));
	}*/
	
	/*@Test
	public void testRegister() {
		BoardVO board=new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		service.register(board);
		log.info("생성된 게시물의 번호: "+board.getBno());
	}*/
	
	/*@Test
	public void testGet() {
		log.info(service.get(393242L));
	}*/
	
	/*@Test
	public void testDelete() {
		log.info("remove result: "+service.remove(393242L));
	}*/
	
	/*@Test
	public void testUpdate() {
		BoardVO board=service.get(393249L);
		if(board==null) {
			return;
		}
		board.setTitle("수정함");
		log.info("modify result: "+service.modify(board));
	}*/
}
