package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.ApiResponse;
import com.blog.payload.CategoryDto;
import com.blog.serviceimpl.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	
	//creating categories
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryServiceImpl.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto> (createCategory,HttpStatus.CREATED);
		
	}

		//update categories
		@PutMapping("/{categoryId}")
		public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId )
		{
			
			CategoryDto updateCategory = this.categoryServiceImpl.updateCategory(categoryDto, categoryId);
			
			return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.ACCEPTED);
		}
		
		
		//delete categories
		@DeleteMapping("/{id}")
		public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
			this.categoryServiceImpl.deleteCategory(id);
			return new ResponseEntity<ApiResponse> (new ApiResponse("category is deleted...!", true),HttpStatus.OK);
		}
		
		
		
		//get categories
		@GetMapping("/{id}")
		public ResponseEntity<CategoryDto>getId(@PathVariable Integer id)
		{
			
			CategoryDto category = this.categoryServiceImpl.getCategory(id);
			
			return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
		}
		
		
		//get All categories
		@GetMapping("/")
		public ResponseEntity<List<CategoryDto>> getAll()
		{
			List<CategoryDto> categories = this.categoryServiceImpl.getCategories();
			return new ResponseEntity<>(categories, HttpStatus.OK);
			
		}
		
	
	


	
	
	
	
	
	
	

}
