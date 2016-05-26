package kr.ranian.web;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.JsonPathExpectationsHelper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ranian.domain.ProductVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration	// 웹애플리케이션이 실행이 된다.
public class ControllerTest {

	@Inject
	private WebApplicationContext context;
	
	private MockMvc mockMVC;
	
	@Before
	public void setup() {
		this.mockMVC = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testDoA() throws Exception {
		mockMVC.perform(MockMvcRequestBuilders.get("/doA"));
	}
	
	@Test
	public void testDoJSON() throws Exception {
		String corectResult = "{\"name\":\"ipad\",\"price\":3000}";
		
		MvcResult result = mockMVC.perform(MockMvcRequestBuilders.get("/doJSON")).andReturn();
		String content = result.getResponse().getContentAsString();
		mockMVC.perform(MockMvcRequestBuilders.get("/doJSON"))
									.andExpect(content().string(corectResult));
		
		assertThat(corectResult, is(content));
		System.out.println("content:" + content);
	}
	
	@Test
	public void testDoJSON2() throws Exception {
		String corectResult = "{\"name\":\"ipad\",\"price\":3000}";
		
		ProductVO product = new ProductVO();
		product.setName("ipad");
		product.setPrice(3000);
		
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(product);
		
		mockMVC.perform(MockMvcRequestBuilders.get("/doJSON"))
				.andExpect(content().string(result)); // or corectResult
	}
	
	// 에러남 이유 모르겠음..
	@Test
	public void testDoJSON3() throws Exception {
		String corectJson = "{'name':'ipad','price':'3000'}";
		
		ProductVO product = new ProductVO();
		product.setName("ipad");
		product.setPrice(3000);
		
		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writeValueAsString(product);
		System.out.println("product:" + result);
		
		mockMVC.perform(MockMvcRequestBuilders.get("/doJSON"))
				.andExpect(jsonPath("name").value("ipad"));
				//.andExpect(content().json(result));
		
	}
}
