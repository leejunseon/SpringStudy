package com.practice.domain;

import lombok.Data;

@Data
public class MailVO {

	private String from;
	private String to;
	private String title;
	private String content;
}
