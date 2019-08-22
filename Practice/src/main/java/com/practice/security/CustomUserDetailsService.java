package com.practice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.practice.domain.MemberVO;
import com.practice.mapper.MemberMapper;
import com.practice.security.domain.CustomUser;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService{
	
	@Setter(onMethod_= {@Autowired})
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		log.warn("Load User By UserName: "+username);//login창에서 입력한 id
		
		MemberVO vo=memberMapper.read(username);
		
		log.warn("queried by member mapper: "+vo);
		
		return vo==null?null:new CustomUser(vo);
		
	}

}
