package kr.ranian.web;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.ranian.domain.MemberVO;
import kr.ranian.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberDAOTest {

	@Inject
	private MemberDAO dao;
	
	@Test
	public void testDAO() throws Exception {
		System.out.println(dao);
	}
	
	@Test
	public void testGetTime() throws Exception {
		System.out.println(dao.getTime());
	}
	
	@Test
	public void testInsertMember() throws Exception {
		
		for (int i = 1; i <= 100; i++) {
			MemberVO vo = new MemberVO();
			vo.setUserid("user" + i);
			vo.setUserpw("user" + i);
			vo.setUsername("USER" + i);
			vo.setEmail("user" + i + "@test.com");
			
			dao.insertMember(vo);
		}
	}
}
