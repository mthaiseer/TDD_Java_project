package com.order.tdd.ordermanagement.repository;

import com.order.tdd.ordermanagement.vo.Category;

public class CategoryTestData {
	
	public static Category java() {
		return new Category("java");
	}
	
	public static Category nameLessThanMinLength() {
		return new Category("ab");
	}
	
	public static Category nameGreaterThanMaxLength() {
		return new Category("its a very long name which should fail while try to insert");
	}
	
	public static Category javaWithId() {
		return new Category(1000L, "java");
	}
	
	public static Category react() {
		return new Category("react");
	}
	
	public static Category algorithms() {
		return new Category("Algorithms");
	}
	
	public static Category algorithmsUpdate() {
		return new Category("Algorithms v2");
	}
	
	public static Category nullVal() {
		return new Category(null);
	}
	
	public static Category empty() {
		return new Category("");
	}
}
