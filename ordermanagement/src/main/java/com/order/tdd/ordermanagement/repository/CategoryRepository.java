package com.order.tdd.ordermanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.order.tdd.ordermanagement.vo.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

}
