package org.zerock.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PageMaker {

	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;		// 화면에 보여지는 페이지 번호의 숫자
	
	private Page page;
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		makePageInfo();
	}

	// cur 13 page -> startPage 11, endPage 20
	private void makePageInfo() {
		// 13 / 10 = 1.3 ceil(1.3) = 2 * 10 = 20
		endPage = (int) (Math.ceil(page.getPage() / (double)displayPageNum) * displayPageNum);
		startPage = (endPage - displayPageNum) + 1;
		
		// totalCount 122, tempEndPage = 12.2 -> 13
		// endPage 20 ------> 13
		int tempEndPage = (int) ( Math.ceil(totalCount / (double)page.getPerPageNum()) );
		if (endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		
		next = endPage * page.getPerPageNum() >= totalCount ? false : true; 
	}
	
	public String makeQuery(int page) {
		
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
					.queryParam("page", page)
					.queryParam("perPageNum", this.page.getPerPageNum())
					.build();
		
//		System.out.println(uriComponents.toUriString());
		return uriComponents.toUriString(); 
	}
	
	public String makeSearch(int page) {
		
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", this.page.getPerPageNum())
				.queryParam("searchType", ((SearchPage)this.page).getSearchType())
				.queryParam("keyword", ((SearchPage)this.page).getKeyword())
				.build();
		
//		System.out.println(uriComponents.toUriString());
		return uriComponents.toUriString(); 
	}
}
