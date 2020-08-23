package com.order.tdd.ordermanagement.service;

import com.order.tdd.ordermanagement.exception.FieldNotValidException;
import com.order.tdd.ordermanagement.vo.Category;

public interface CategoryService {
	
	Category save(Category category) throws FieldNotValidException;

}
