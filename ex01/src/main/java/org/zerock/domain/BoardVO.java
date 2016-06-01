package org.zerock.domain;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

/**
 * tbl_board
 * @author kjlee
 *
 */
@Getter @Setter @ToString
public class BoardVO {

	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private int viewcnt;
	
}
