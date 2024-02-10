package com.blog.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.util.StreamUtils;
//import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.config.AppConstants;
import com.blog.payload.ApiResponse;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.service.FileService;
import com.blog.service.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	// private Object StreamUtils.;

	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
													 @PathVariable Integer userId,
													 @PathVariable Integer categoryId) {
		PostDto createPost = this.postService.createPost(postDto,userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

	}

	// get post by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
		List<PostDto> postsByUser = this.postService.getPostsByUser(userId);

		return new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);

	}

	// get post by category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		List<PostDto> postsByCategory = this.postService.getPostsByCategory(categoryId);

		return new ResponseEntity<>(postsByCategory, HttpStatus.OK);

	}

	// get post by post id
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostByPostID(@PathVariable Integer postId) {
		PostDto postByID = this.postService.getPostByID(postId);
		return new ResponseEntity<PostDto>(postByID, HttpStatus.OK);

	}

	// get all post
	@GetMapping("/posts")
	ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		PostResponse postResponse = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);   //

		
		
		return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);

	}

	// update post
	@PutMapping("/posts/{postId}")
	ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

	// delete post

	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("post sucessfully deleted", true);

	}

	// search
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable String keyword) {
		List<PostDto> searchedPost = this.postService.searchPost(keyword);
		return new ResponseEntity<List<PostDto>>(searchedPost, HttpStatus.OK);
	}

	// upload post image
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {
		
		PostDto postDto = postService.getPostByID(postId);
		
		String fileName = this.fileService.uploadImage(path, image);
							postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);

		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);

	}

	// for get image
	@GetMapping(value = "post/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		// StreamUtils.copy(resource,response.getOutputStream());
		// StreamUtils

		StreamUtils.copy(resource, response.getOutputStream());
	}

}
