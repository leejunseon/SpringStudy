package com.practice.mapper;

import com.practice.domain.AuthVO;
import com.practice.domain.MemberVO;

public interface MemberMapper {
	
	public MemberVO read(String userid);
	
	public int insertMember(MemberVO member);
	
	public int insertAuth(AuthVO auth);
}
