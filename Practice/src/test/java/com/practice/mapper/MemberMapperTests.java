package com.practice.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.practice.domain.MemberVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class MemberMapperTests {
	
	@Setter(onMethod_=@Autowired)
	private MemberMapper mapper;
	
	@Setter(onMethod_=@Autowired)
	private PasswordEncoder pwencoder;
	
	/*@Test
	public void testRead() {
		MemberVO vo=mapper.read("dlwnstjs");
		log.info(vo);
		vo.getAuthList().forEach(auth->log.info(auth));
	}*/
	
	/*@Test
	public void testCheckReduplication() {
		String id="dlwnstjs";
		log.info("number of id: "+mapper.checkReduplication(id));
	}*/
	
	@Test
	public void testUpdate() {
		MemberVO vo=new MemberVO();
		vo.setUserid("dlwnstjs");
		vo.setUserpw(pwencoder.encode("dkxltmxm135"));
		log.info("update result: "+mapper.updateMember(vo));
	}
}
