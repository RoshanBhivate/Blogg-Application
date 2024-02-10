package com.blog.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CategoryDto;
import com.blog.repository.CategoryRepo;
import com.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelMapper.map(categoryDto, Category.class);
	     Category addedCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(addedCat, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
	    Category cat = this.categoryRepo.findById(categoryId)
	    		.orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));
	    cat.setCategoryTitle(categoryDto.getCategoryTitle());
	    cat.setCategoryDescription(categoryDto.getCategoryDescription());
	    
	    Category updatecat = this.categoryRepo.save(cat);
	    
		//return this.modelMapper.map(updatecat, CategoryDto.class);
	    
	   return  this.modelMapper.map(updatecat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {

		  Category cat = this.categoryRepo.findById(categoryId)
		    		.orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));
		  this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		
		Category cat = this.categoryRepo.findById(categoryId)
	    		.orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));
		
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<Category> findAll = this.categoryRepo.findAll();
		
		List<CategoryDto> catDto = findAll.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList()) ;
		return catDto;
		
		
	}

}
