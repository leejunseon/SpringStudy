package com.practice.domain;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberVO {
	
	@NotEmpty
	private String userid;
	
	@NotEmpty
	private String userpw;
	
	@NotEmpty
	private String userName;
	
	@NotEmpty
	@Email
	private String email;
	
	
	private boolean enabled;
	
	private Date regDate;
	private Date updateDate;
	private List<AuthVO> authList;
}
