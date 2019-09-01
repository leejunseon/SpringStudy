package com.practice.service;

import com.practice.domain.AuthVO;
import com.practice.domain.MemberVO;

public interface MemberService {
	
	public int insertMember(MemberVO member);
	
	public int insertAuth(AuthVO auth);

	public int checkReduplication(String id);
	
	public int updateMember(MemberVO member);
}
