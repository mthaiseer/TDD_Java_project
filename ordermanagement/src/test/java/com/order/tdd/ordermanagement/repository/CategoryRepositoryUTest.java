package com.order.tdd.ordermanagement.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.order.tdd.ordermanagement.vo.Category;

import static com.order.tdd.ordermanagement.repository.CategoryTestData.*;

@DataJpaTest
public class CategoryRepositoryUTest {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void testAddNewCategory() {
		Category created = categoryRepository.save(java());
		assertThat(created).isNotNull();
		assertEquals(java().getName(), created.getName());
	}
	
	@Test
	void testFindById() {
		Category categoryFind = categoryRepository.findById(100L).get();
		assertThat(categoryFind).isNotNull();
		assertThat(categoryFind.getId()).isEqualTo(100L);
		assertThat(categoryFind.getName()).isEqualTo(algorithms().getName());
	}
	
	@Test
	void testUpdateCategory() {
		Category categoryToUpdate = categoryRepository.findById(100L).get();
		categoryToUpdate.setName(algorithmsUpdate().getName());
		categoryToUpdate =  categoryRepository.save(categoryToUpdate);
		assertThat(categoryToUpdate).isNotNull();
		assertThat(categoryToUpdate.getName()).isEqualTo(algorithmsUpdate().getName());
		
	}
	
	@Test
	void deleteCategory() {
		Category categoryToDelete = categoryRepository.findById(100L).get();
		categoryRepository.delete(categoryToDelete);
		
		Optional<Category> notFound = categoryRepository.findById(100L);
		assertThat(notFound).isEmpty();
	}
	
	@Test
	void testNullNameShouldFail() {
		try {
			categoryRepository.save(nullVal());
		}catch(Exception e) {
			fail("It should fail");
		}
	
	}

}
