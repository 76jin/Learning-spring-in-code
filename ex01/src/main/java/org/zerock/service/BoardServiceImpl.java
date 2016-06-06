package org.zerock.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Page;
import org.zerock.domain.SearchPage;
import org.zerock.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;
	
	@Override
	public void regist(BoardVO board) throws Exception {
		dao.create(board);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<BoardVO> listPage(Page page) throws Exception {
		return dao.listPage(page);
	}

	@Override
	public int listCountPaging(Page page) throws Exception {
		return dao.countPaging(page);
	}

	@Override
	public List<BoardVO> listSearch(SearchPage page) throws Exception {
		return dao.listSearch(page);
	}

	@Override
	public int listSearchCount(SearchPage page) throws Exception {
		return dao.listSearchCount(page);
	}

}
