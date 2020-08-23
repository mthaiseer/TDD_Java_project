package com.order.tdd.ordermanagement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.order.tdd.ordermanagement.service.CategoryService;
import com.order.tdd.ordermanagement.service.CategoryServiceImpl;
import static com.order.tdd.ordermanagement.repository.CategoryTestData.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CategoryServiceImpl categoryService;
	
	
	@Test
	void testCategoryPost() throws Exception {
		
		when(categoryService.save(java())).thenReturn(javaWithId());
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
			.post("/api/category")
			.accept(MediaType.APPLICATION_JSON)
			.content("{ name : java}");
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andExpect((ResultMatcher) content().json("{id: 2,name:laptop}"))
				.andReturn();
		assertThat(result).isNotNull();
		
	}
	
	@Test
	@Disabled
	void testCategoryPostV2() throws Exception {
		
		when(categoryService.save(java())).thenReturn(javaWithId());
		
		mockMvc.perform(post("/api/category")
		           .contentType(MediaType.APPLICATION_JSON)
		           .content("{id: 2,name:laptop}") 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isCreated())
		           .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
		           .andExpect(jsonPath("$.name").value("java")); 
		
	}

}
