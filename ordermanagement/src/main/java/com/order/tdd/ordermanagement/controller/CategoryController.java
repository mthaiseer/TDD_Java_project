package com.order.tdd.ordermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.order.tdd.ordermanagement.exception.FieldNotValidException;
import com.order.tdd.ordermanagement.service.CategoryService;
import com.order.tdd.ordermanagement.vo.Category;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Category save(@RequestBody Category category) throws FieldNotValidException{
		
		Category created = categoryService.save(category);
		//return  ResponseEntity.status(HttpStatus.CREATED).body(created);
		return created;
	}

}
