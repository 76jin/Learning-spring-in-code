package org.zerock.domain;

import lombok.ToString;

@ToString
public class Page {
	
	private int page;			// 페이지 번호
	private int perPageNum;		// 목록 페이지에 출력하는 데이터의 개수
	
	public Page() {
		page = 1;
		perPageNum = 10;
	}
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		if (page <= 0) {
			page = 1;
			return;
		}
		this.page = page;
	}
	
	// method for MyBatis SQL Mapper
	public int getPerPageNum() {
		return perPageNum;
	}
	
	public void setPerPageNum(int perPageNum) {
		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
		}
		this.perPageNum = perPageNum;
	}
	
	// method for MyBatis SQL Mapper
	private int getPageStart() {
		return (this.page - 1) * perPageNum;
	}

}
