package org.zerock.test;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
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
}
