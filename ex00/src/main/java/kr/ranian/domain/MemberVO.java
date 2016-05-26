package kr.ranian.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
create table tbl_member (
	userid varchar(50) not null,
    userpw varchar(50) not null,
    username varchar(50) not null,
    email varchar(100),
    regdate timestamp default now(),
    updatedate timestamp default now(),
    primary key(userid)
);
 * @author kjlee
 *
 */

@Getter @Setter @ToString 
//@ToString(exclude="userpw")
public class MemberVO {

	private String userid;
    private String userpw;
    private String username;
    private String email;
    
    private Date regdate;
    private Date updatedate;
    
	
}
