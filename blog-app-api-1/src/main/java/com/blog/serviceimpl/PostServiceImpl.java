package com.blog.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.repository.CategoryRepo;
import com.blog.repository.PostRepo;
import com.blog.repository.UserRepo;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	// create post
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user", "user Id", userId));

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category id", categoryId));

		
		  Post post = this.modelMapper.map(postDto, Post.class);
		  
		  post.setAddedDate(new Date());
		  
		  post.setImageName("default.png");
		  
		  post.setUser(user); post.setCategory(category);
		 
		
		/*
		Post post = new Post();
		
		BeanUtils.copyProperties(postDto, post);
		
		post.setAddedDate(new Date());
		post.setImageName("default.png");
		post.setUser(user);
		post.setCategory(category);
		*/
		Post newPost = this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDto.class);

	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post with id not found", "post Id", postId));

		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post save = this.postRepo.save(post);
		return this.modelMapper.map(save, PostDto.class);

	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post with id not found", "post Id", postId));

		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		// int pageNumber=1;
		// int pageSize=3;
		Sort sort = null;

		sort = (sortDir.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		/*
		 * if(sortDir.equalsIgnoreCase("asc")) { sort=Sort.by(sortBy).ascending(); }
		 * else { sort=Sort.by(sortBy).descending(); }
		 */

		 Pageable p=PageRequest.of(pageNumber, pageSize ,Sort.by(sortBy));

		// Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allPost = pagePost.getContent();

		List<PostDto> postDtos = allPost.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostDto getPostByID(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("post id not found", "post Id", postId));
		PostDto map = this.modelMapper.map(post, PostDto.class);

		return map;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("categoery not found", "category Id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(cat);

		List<PostDto> postDto = posts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		return postDto;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found", "User Id ", userId));
		List<Post> users = this.postRepo.findByUser(user);
		List<PostDto> postDtos = users.stream().map((userr) -> modelMapper.map(userr, PostDto.class))
				.collect(Collectors.toList());

		return postDtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
	List<PostDto> collect = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		
		return collect;
	}
	
	


}
