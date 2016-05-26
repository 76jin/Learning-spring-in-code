package kr.ranian.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.ranian.domain.MemberVO;

@Repository
public class MeberDAOImpl implements MemberDAO {

	public static final String NAMESPACE = "kr.ranian.mapper.MemberMapper";
	
	@Inject
	private SqlSession sqlSession;
	

	@Override
	public String getTime() {
		return sqlSession.selectOne(NAMESPACE + ".getTime");
	}


	@Override
	public void insertMember(MemberVO vo) throws Exception {
		sqlSession.insert(NAMESPACE + ".insertMember", vo);
	}

}
