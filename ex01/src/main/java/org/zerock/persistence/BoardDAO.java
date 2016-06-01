package org.zerock.persistence;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardDAO {

	// crud + list 
	public void create(BoardVO board) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void update(BoardVO board) throws Exception;
	
	public void delete(Integer bno) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	// 같은 Connection에서 insert 한 마지막 컬럼(auto_increament 적용된 컬럼) 값을 가져옴.
	// 그렇지 않으면 0 리턴함.
	public Integer getLastInsertId() throws Exception; 
	
}
