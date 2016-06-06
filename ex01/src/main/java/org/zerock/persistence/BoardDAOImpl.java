package org.zerock.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Page;
import org.zerock.domain.SearchPage;

@Repository
public class BoardDAOImpl implements BoardDAO {

	public static final String NAMESPACE = "org.zerock.mapper.BoardMapper";
	
	@Inject
	private SqlSession session;
	
	@Override
	public void create(BoardVO board) throws Exception {
		session.insert(NAMESPACE + ".create", board);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return session.selectOne(NAMESPACE + ".read", bno);
	}

	@Override
	public void update(BoardVO board) throws Exception {
		session.update(NAMESPACE + ".update", board);
	}

	@Override
	public void delete(Integer bno) throws Exception {
		session.delete(NAMESPACE + ".delete", bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return session.selectList(NAMESPACE + ".listAll");
	}

	@Override
	public Integer getLastInsertId() throws Exception {
		return session.selectOne(NAMESPACE + ".getLastInsertId");
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10; // 0, 10, 20
		return session.selectList(NAMESPACE + ".listPage", page);
	}

	@Override
	public List<BoardVO> listPage(Page page) throws Exception {
		return session.selectList(NAMESPACE + ".listPage", page);
	}

	@Override
	public int countPaging(Page page) throws Exception {
		return session.selectOne(NAMESPACE + ".countPaging", page);
	}

	@Override
	public List<BoardVO> listSearch(SearchPage page) throws Exception {
		return session.selectList(NAMESPACE + ".listSearch", page);
	}

	@Override
	public int listSearchCount(SearchPage page) throws Exception {
		return session.selectOne(NAMESPACE + ".listSearchCount", page);
	}

}
