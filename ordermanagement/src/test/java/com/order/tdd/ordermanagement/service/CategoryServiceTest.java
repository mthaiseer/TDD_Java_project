package com.order.tdd.ordermanagement.service;

import static com.order.tdd.ordermanagement.repository.CategoryTestData.java;
import static com.order.tdd.ordermanagement.repository.CategoryTestData.javaWithId;
import static com.order.tdd.ordermanagement.repository.CategoryTestData.nameGreaterThanMaxLength;
import static com.order.tdd.ordermanagement.repository.CategoryTestData.nameLessThanMinLength;
import static com.order.tdd.ordermanagement.repository.CategoryTestData.nullVal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.order.tdd.ordermanagement.exception.FieldNotValidException;
import com.order.tdd.ordermanagement.exception.InvalidFieldException;
import com.order.tdd.ordermanagement.repository.CategoryRepository;
import com.order.tdd.ordermanagement.vo.Category;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@Slf4j
public class CategoryServiceTest {

	@InjectMocks
	private static CategoryServiceImpl orderService;

	@Mock
	private CategoryRepository categoryRepository;

	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		orderService.setValidator(validator);
	}


	@Test
	void testSaveCategory() {
		when(categoryRepository.save(java())).thenReturn(javaWithId());
		Category javaCategory = orderService.save(java());
		assertThat(javaCategory).isNotNull();
		assertThat(javaCategory.getName()).isEqualTo(java().getName());
	}

	@Test
	void testNameNullShouldFail() {

		Assertions.assertThrows(FieldNotValidException.class, ()->{
			orderService.save(nullVal());
		});
	}
	
	@Description("It should fail if name length less than 3 characters")
	@Test
	void testNameLessThanMinShouldfail() {

		Assertions.assertThrows(FieldNotValidException.class, ()->{
			orderService.save(nameLessThanMinLength());
		});
	}
	
	@Description("It should fail if name length exceeds 20 characters")
	@Test
	void testNameGreaterThanMaxShouldfail() {

		Assertions.assertThrows(FieldNotValidException.class, ()->{
			orderService.save(nameGreaterThanMaxLength());
		});
	}
	
	@Test
	void testFindCategoryNullIDFail() {
		Assertions.assertThrows(InvalidFieldException.class, ()->{
			orderService.findById(null);
		});
		
	}
	
	@Test
	void testFindCategoryNotFoundReturnNull() {
		when(categoryRepository.findById(100L)).thenReturn(Optional.empty());
		Category category = orderService.findById(100L);
		assertThat(category).isNull();
	}
	
	@Test
	void testFindCategorySucccess() {
		when(categoryRepository.findById(1000L)).thenReturn(Optional.of(javaWithId()));
		Category category = orderService.findById(1000L);
		assertThat(category).isNotNull();
		assertThat(category.getId()).isEqualTo(1000L);
		assertThat(category.getName()).isEqualTo(javaWithId().getName());
		
	}
	

}
