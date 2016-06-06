package org.zerock.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Page;
import org.zerock.domain.SearchPage;
import org.zerock.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {

	public static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	
	@Inject
	private BoardDAO dao;
	
	@Test
	public void testDI() throws Exception {
		logger.info("test DAO DI:{}", dao.toString());
	}
	
	@Test
	public void testCreate() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("새로운 글을 넣습니다.");
		board.setContent("새로운 글을 넣습니다.");
		board.setWriter("user01");
		
		dao.create(board);
	}
	
	@Test
	public void testRead() throws Exception {
		logger.info("dao.read(1):{}", dao.read(1));
	}
	
	@Test
	public void testUpdate() throws Exception {
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("수정된 글입니다.");
		board.setContent("수정된 내용입니다.");
		
		dao.update(board);
	}
	
	@Test
	public void testDelete() throws Exception {
		dao.delete(14);
	}
	
	@Test
	public void testListAll() throws Exception {
		logger.info("## listAll:{}", dao.listAll());
	}
	
	@Test
	public void testListPage1() throws Exception {
		int page = 4;
		List<BoardVO> list = dao.listPage(page);
		
		for (BoardVO board : list) {
			logger.info(board.getBno() + ":" + board.getTitle());
		}
	}
	@Test
	public void testListPage() throws Exception {
		Page page = new Page();
		page.setPage(2);
		page.setPerPageNum(20);
		
		List<BoardVO> list = dao.listPage(page);
		
		for (BoardVO board : list) {
			logger.info(board.getBno() + ":" + board.getTitle());
		}
	}
	
	@Test
	public void testURI1() throws Exception {
		
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
					.path("/board/read")
					.queryParam("bno", 12)
					.queryParam("perPageNum", 20)
					.build();
		
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}
	
	@Test
	public void testURI2() throws Exception {
		
		UriComponents uriComponents = 
				UriComponentsBuilder.newInstance()
					.path("/{module}/{page}")
					.queryParam("bno", 12)
					.queryParam("perPageNum", 20)
					.build()
					.expand("board", "read")
					.encode();
		
		logger.info("/board/read?bno=12&perPageNum=20");
		logger.info(uriComponents.toString());
	}
	
	@Test
	public void testDynamic1() throws Exception {
		
		SearchPage page = new SearchPage();
		page.setPage(1);
		page.setKeyword("01");
		page.setSearchType("n");
//		page.setSearchType("t");
//		page.setSearchType("c");
//		page.setSearchType("w");
//		page.setSearchType("tc");
//		page.setSearchType("cw");
//		page.setSearchType("tcw");
		
		logger.info("=========================");
		List<BoardVO> list = dao.listSearch(page);
		
		for (BoardVO board : list) 
			logger.info("{}:{}", board.getBno(), board.getTitle());
		logger.info("=========================");
		
//		logger.info("COUNT:{}", dao.listSearchCount(page)) ;
	}
}
