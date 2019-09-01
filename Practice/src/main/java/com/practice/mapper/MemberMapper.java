package com.practice.mapper;

import org.apache.ibatis.annotations.Param;

import com.practice.domain.AuthVO;
import com.practice.domain.MemberVO;

public interface MemberMapper {
	
	public MemberVO read(String userid);
	
	public int insertMember(MemberVO member);
	
	public int insertAuth(AuthVO auth);

	public int checkReduplication(String id);
	
	public int getEmail(@Param("id")String id,@Param("email")String email);
}
