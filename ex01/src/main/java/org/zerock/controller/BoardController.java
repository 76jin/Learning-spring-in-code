package org.zerock.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	public static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGET() {
		logger.info("register get.........");
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("register post.........\n board:{}", board.toString());
		
		service.regist(board);
		
		rttr.addFlashAttribute("result", "success");
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/listAll", method=RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("listAll ..........");
		model.addAttribute("list", service.listAll());
	}
	
	// read?bno=18
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void read(@RequestParam("bno") Integer bno, Model model) throws Exception {
		logger.info("read?bno={} ..........", bno);
		model.addAttribute("boardVO", service.read(bno));
	}
	
	// remove bno=18
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@RequestParam("bno") Integer bno, RedirectAttributes rttr) throws Exception {
		logger.info("remove bno={} ..........", bno);
		if (bno != null && bno > 0) {
			service.remove(bno);
			rttr.addFlashAttribute("result", "success");
		} else {
			logger.warn("bno is wrong! bno:{}", bno);
		}
		return "redirect:/board/listAll";
	}
	
	// modify?bno=17
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") Integer bno, Model model) throws Exception {
		logger.info("modify?bno={} ..........", bno);
		model.addAttribute("boardVO", service.read(bno));
	}
	
	// modify post boardVO
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("modify post board={} ..........", board);
		if (board != null) {
			service.modify(board);
			rttr.addFlashAttribute("result", "success");
		} else {
			logger.warn("boardVO is null!");
		}
		return "redirect:/board/listAll";
	}
	
}
