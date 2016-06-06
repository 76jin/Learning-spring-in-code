package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
public class SearchPage extends Page {

	private String searchType;
	private String keyword;
	
	@Override
	public String toString() {
		return super.toString()
				+ " SearchPage [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
	
	
}
