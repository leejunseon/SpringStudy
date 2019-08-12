package com.practice.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.practice.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	private Long[] bnoArr= {393421L,393401L};
	
	@Setter(onMethod_=@Autowired)
	private ReplyMapper mapper;
	
	@Test
	public void testMapper() {
		log.info(mapper);
	}
	
	/*@Test
	public void testCreate() {
		IntStream.rangeClosed(1,10).forEach(i->{
			ReplyVO vo=new ReplyVO();
			vo.setBno(bnoArr[i%2]);
			vo.setReply("댓글 테스트"+i);
			vo.setReplyer("replyer"+i);
			mapper.insert(vo);
		});
	}*/
	
	/*@Test
	public void testRead() {
		Long targetRno=5L;
		ReplyVO vo=mapper.read(targetRno);
		log.info(vo);
	}*/
	
	/*@Test
	public void testDelete() {
		Long targetRno=12L;
		mapper.delete(targetRno);
	}*/
	
	/*@Test
	public void testUpdate() {
		Long targetRno=10L;
		ReplyVO vo=mapper.read(targetRno);
		vo.setReply("수정스~");
		int count=mapper.update(vo);
		log.info("update count : "+count);
	}*/
	
	/*@Test
	public void testList() {
		List<ReplyVO> replies=mapper.getReplies(393421L);
		replies.forEach(reply->log.info(reply));
	}*/

}
