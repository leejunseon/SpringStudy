package com.practice.security.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.practice.domain.MemberVO;

//CustomUserDetailsService에서 UserDetails를 반환함. UserDetails를 프로젝트의 MemberVO에 맞게 변형하기 위해 User를 상속한 CustomUser 만듦
public class CustomUser extends User{

	private static final long serialVersionUID=1L;
	private MemberVO member;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	
	public CustomUser(MemberVO vo) {
		super(vo.getUserid(),vo.getUserpw(),vo.getAuthList().stream()
				.map(auth->new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList()));
		this.member=vo;
	}

}
