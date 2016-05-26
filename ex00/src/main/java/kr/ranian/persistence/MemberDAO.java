package kr.ranian.persistence;

import kr.ranian.domain.MemberVO;

public interface MemberDAO {

	public String getTime();
	
	public void insertMember(MemberVO vo) throws Exception;
}
