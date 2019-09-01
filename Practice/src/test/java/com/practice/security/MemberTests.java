package com.practice.security;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.practice.domain.AuthVO;
import com.practice.domain.MemberVO;
import com.practice.mapper.MemberMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j
public class MemberTests {
	
	@Setter(onMethod_=@Autowired)
	private PasswordEncoder pwencoder;
	
	@Setter(onMethod_=@Autowired)
	private DataSource ds;
	
	@Setter(onMethod_=@Autowired)
	MemberMapper mapper;
	
	@Test
	public void testInsertMember() {
		
		MemberVO member=new MemberVO();
		member.setUserid("ljs921026");
		member.setUserpw(pwencoder.encode("dkxltmxm135"));
		member.setUserName("¿Ã¡ÿº±");
		log.info("memberInsert Test: "+mapper.insertMember(member));

	}
	
	/*@Test
	public void testCheckReduplication() {
		String id="dlwnstjs";
		log.info("number of id: "+mapper.checkReduplication(id));
	}*/
	
	/*Test
	public void testInsertAuth() {
		
		AuthVO auth=new AuthVO();
		auth.setUserid("ljs921026");
		auth.setAuth("ROLE_USER");
		log.info("authInsert Test: "+mapper.insertAuth(auth));
	}*/

}
