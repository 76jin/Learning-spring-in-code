package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Page;
import org.zerock.domain.SearchPage;

public interface BoardService {

	public void regist(BoardVO board) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void modify(BoardVO board) throws Exception;
	
	public void remove(Integer bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listPage(Page page) throws Exception;
	
	public int listCountPaging(Page page) throws Exception;	// totalCount 반환
	
	public List<BoardVO> listSearch(SearchPage page) throws Exception; // 검색 + 페이징 처리
	
	public int listSearchCount(SearchPage page) throws Exception;
	
}
