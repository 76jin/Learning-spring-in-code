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
import org.zerock.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardServiceTest {

	public static final Logger logger = LoggerFactory.getLogger(BoardServiceTest.class);
	
	@Inject
	private BoardService service;
	
	@Test
	public void testDI() throws Exception {
		logger.info("test DI BoardService:{}", service);
	}
	
	@Test
	public void testRegist() throws Exception {
		BoardVO board = new BoardVO();
		board.setTitle("테스트 제목");
		board.setContent("테스트 본문");
		board.setWriter("user02");
		
		service.regist(board);
	}
	
	@Test
	public void testRead() throws Exception {
		logger.info("## testRead:{}", service.read(15));
	}
	
	@Test
	public void testModify() throws Exception {
		BoardVO board = new BoardVO();
		board.setBno(15);
		board.setTitle("수정된 제목");
		board.setContent("수정된 본문");
		
		service.modify(board);
	}
	
	@Test
	public void testRemove() throws Exception {
		service.remove(15);
	}
	
	@Test
	public void testListAll() throws Exception {
		logger.info("## testListAll():\n{}", service.listAll());
	}

}
