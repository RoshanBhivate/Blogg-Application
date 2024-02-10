package com.blog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.blog.entity.Post;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;

public interface PostService {
	
	//creaet
	PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);
	
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	
	//delete
	void deletePost(Integer postId);
	
	

	//getAll post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir);

	
	//get single post  by id
	PostDto getPostByID(Integer postId);
	
	//get all post by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all post by user
	List<PostDto> getPostsByUser(Integer userId);
	
	
	//search post by keyword
	List<PostDto> searchPost(String keyword);
	
	
	
}
