package org.zerock.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// 공통의 Exception 처리 전용 객체
@ControllerAdvice
public class CommonExceptionAdvice {

	public static final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);
	
	/**
	 *  공통 Exception 처리 전용 메소드
	 *  - 테스트 방법
	 *  	1. 존재하지 않는 게시물의 번호를 파라미터로 전달했을 경우
	 *  		- /board/read?bno=99999
	 *  	2. 잘못된 형식의 게시물 번호를 파라미터로 전달했을 경우
	 *  		-/board/read?bno=a
	 * @param e
	 * @return
	 */
	/*
	@ExceptionHandler(Exception.class)
	public String common(Exception e) {
		logger.info("common log:{}", e.toString());
		return "error_common";
	}
	*/
	@ExceptionHandler(Exception.class)
	public ModelAndView common(Exception e) {
		logger.info("common log:{}", e.toString());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/error_common");
		mav.addObject("exception", e);
		return mav;
	}
}
