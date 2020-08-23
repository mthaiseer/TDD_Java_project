package com.order.tdd.ordermanagement.service;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.tdd.ordermanagement.exception.FieldNotValidException;
import com.order.tdd.ordermanagement.exception.InvalidFieldException;
import com.order.tdd.ordermanagement.repository.CategoryRepository;
import com.order.tdd.ordermanagement.vo.Category;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	Validator validator;
	
	public CategoryServiceImpl() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public Category save(Category category)throws FieldNotValidException {
		validate(category);
		return categoryRepository.save(category);
	}
	
	public Category findById(Long id) {
		if(id == null) {
			throw new InvalidFieldException("ID cannot be null or empty");
		}
		Optional<Category> categoryOptional = categoryRepository.findById(id);
		
		if(categoryOptional.isEmpty()) {
			return null;
		}
		
		return categoryRepository.findById(id).get();
	}

	private void validate(Category category) {
		final Set<ConstraintViolation<Category>> errors = validator.validate(category);
		Iterator<ConstraintViolation<Category>> iterator = errors.iterator();
		if(iterator.hasNext()) {
			ConstraintViolation<Category> violation = iterator.next();
			throw new FieldNotValidException(violation.getPropertyPath().toString(), violation.getMessage());
		}
	}

	public void setValidator(Validator validator) {
		this.validator= validator;
	}



}
