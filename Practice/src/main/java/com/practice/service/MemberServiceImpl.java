package com.practice.service;

import org.springframework.stereotype.Service;

import com.practice.domain.AuthVO;
import com.practice.domain.MemberVO;
import com.practice.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

	private MemberMapper mapper;
	
	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		log.info("MemberService : insertMember - "+member);
		return mapper.insertMember(member);
	}

	@Override
	public int insertAuth(AuthVO auth) {
		// TODO Auto-generated method stub
		log.info("MemberService : insertAuth - "+auth);
		return mapper.insertAuth(auth);
	}

	@Override
	public int checkReduplication(String id) {
		// TODO Auto-generated method stub
		log.info("MemberService : checkReduplication - "+id);
		return mapper.checkReduplication(id);
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return mapper.updateMember(member);
	}

}
