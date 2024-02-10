package com.blog.service;

import java.util.List;

import com.blog.payload.CategoryDto;

public interface CategoryService {
	
	
	//create
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	//delete
	public void deleteCategory(Integer categoryId);

	
	//get
	public CategoryDto getCategory(Integer categoryId);

	
	
	//getAll
	public List<CategoryDto> getCategories();

	
	
	

}
