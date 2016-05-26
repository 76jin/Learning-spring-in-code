package kr.ranian.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.ranian.domain.ProductVO;

@Controller
public class SimpleController {

	public static final Logger logger = LoggerFactory.getLogger(SimpleController.class);
	
	@RequestMapping("/doA")
	public void doA() {
		logger.info("doA............");
	}
	
	@RequestMapping("/doC")
	public String doC(@ModelAttribute("msg") String msg) {
		logger.info("doc.............");
		logger.info("MSG:" + msg);
		
		return "sampleC";
	}
	
	@RequestMapping("/doD")
	public String doD(Model model) {
		
		ProductVO product = new ProductVO();
		product.setName("ipad");
		product.setPrice(3000);
		
		model.addAttribute("product", product);
		
		return "productDetail";
	}
	
	@RequestMapping("/doE")
	public String doE(RedirectAttributes rttr) {
		logger.info("doE...........");
		
		rttr.addFlashAttribute("msg", "This message is redirected!!!!");
		return "redirect:/doF";
	}
	
	@RequestMapping("/doF")
	public void doF() {
		logger.info("doF..........");
	}
	
	@RequestMapping("/doJSON")
	public @ResponseBody ProductVO doJSON() {
		
		ProductVO product = new ProductVO();
		product.setName("ipad");
		product.setPrice(3000);
		
		return product;
	}
	
}
