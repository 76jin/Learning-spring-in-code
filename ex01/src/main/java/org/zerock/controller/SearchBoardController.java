package org.zerock.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Page;
import org.zerock.domain.PageMaker;
import org.zerock.domain.SearchPage;
import org.zerock.service.BoardService;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {

	public static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Inject
	private BoardService service;
	
	// list?page=1&perPageNum=10&searchType=t&keyword=01
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void listPage(@ModelAttribute("page") SearchPage page, Model model) throws Exception {
		logger.info("list page:{}..........", page);
		
//		model.addAttribute("list", service.listPage(page));	// list info
		model.addAttribute("list", service.listSearch(page));	// list info
		PageMaker pageMaker = new PageMaker();
		pageMaker.setPage(page);
//		pageMaker.setTotalCount(service.listCountPaging(page));
		pageMaker.setTotalCount(service.listSearchCount(page));
		
		model.addAttribute("pageMaker", pageMaker);		// page info
	}
	
	// readPage?page=10&perPageNum=20&bno=18
	@RequestMapping(value="/readPage", method=RequestMethod.GET)
	public void readPage(@RequestParam("bno") Integer bno, 
					@ModelAttribute("page") SearchPage page,
					Model model) throws Exception {
		logger.info("readPage?bno={}, page={} ..........", bno, page.toString());
		model.addAttribute(service.read(bno));
	}
	
	// remove bno=18
	@RequestMapping(value="/removePage", method=RequestMethod.POST)
	public String removePage(@RequestParam("bno") int bno, 
			SearchPage page,
			RedirectAttributes rttr) throws Exception {
		logger.info("removePage bno={}, page={} ..........", bno, page.toString());
		if (bno > 0) {
			service.remove(bno);
			rttr.addAttribute("page", page.getPage());
			rttr.addAttribute("perPageNum", page.getPerPageNum());
			rttr.addAttribute("searchType", page.getSearchType());
			rttr.addAttribute("keyword", page.getKeyword());
			rttr.addFlashAttribute("result", "success");
		} else {
			logger.warn("bno is wrong! bno:{}", bno);
		}
		return "redirect:/sboard/list";
	}
	
	// modify post boardVO
	@RequestMapping(value="/modifyPage", method=RequestMethod.GET)
	public void modifyPageGET(@RequestParam("bno") Integer bno, 
			@ModelAttribute("page") SearchPage page,
			Model model) throws Exception {
		logger.info("modifyPage?bno={}, page={} ..........", bno, page.toString());
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.POST)
	public String modifyPagePOST(BoardVO board, 
			SearchPage page,
			RedirectAttributes rttr) throws Exception {
		logger.info("modifyPage post board={}, page={} ..........", board, page.toString());
		if (board != null) {
			service.modify(board);
			rttr.addAttribute("page", page.getPage());
			rttr.addAttribute("perPageNum", page.getPerPageNum());
			rttr.addAttribute("searchType", page.getSearchType());
			rttr.addAttribute("keyword", page.getKeyword());
			rttr.addFlashAttribute("result", "success");
		} else {
			logger.warn("boardVO is wrong! boardVO:", board);
		}
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public void registerGET() {
		logger.info("register get.........");
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes rttr) throws Exception {
		logger.info("register post.........\n board:{}", board.toString());
		
		service.regist(board);
		
		rttr.addFlashAttribute("result", "success");
		
		return "redirect:/sboard/list";
	}
}
