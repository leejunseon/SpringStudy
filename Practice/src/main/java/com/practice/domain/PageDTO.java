/*package com.practice.domain;

import lombok.Data;

@Data
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev,next;
	
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri,int total) {
		this.cri=cri;
		this.total=total;
		
		this.endPage=(int)(Math.ceil(cri.getPageNum()/10.0))*10;//끝 페이지(10단위)
		this.startPage=this.endPage-9;//시작 페이지
		
		int realEnd=(int)(Math.ceil((total*1.0)/cri.getAmount()));//진짜 끝 페이지
		
		if(realEnd<this.endPage)
			this.endPage=realEnd;
		
		this.prev=this.startPage>1;
		this.next=this.endPage<realEnd;
	}
}
*/