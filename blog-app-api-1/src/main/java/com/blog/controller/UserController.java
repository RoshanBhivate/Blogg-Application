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
import com.blog.payload.UserDto;
import com.blog.service.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	

	
	@Autowired
	private UserService userService;
	
	//POST - adding user
	@PostMapping("/")
	public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUser = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUser,HttpStatus.CREATED);
	}
	
	//UPDATE - updating user
	@PutMapping("/{uid}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer uid)
	{
		UserDto updateUser = this.userService.updateUser(userDto, uid);
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
		
	}
	
	//GET -GET user by id
	@GetMapping("{uid}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer uid)
	{
		UserDto userById = this.userService.getUserById(uid);
		return new ResponseEntity<>(userById,HttpStatus.OK);
	}
	
	//GetAll
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser()
	{
		List<UserDto> allUser = this.userService.getAllUser();
		return new ResponseEntity<>(allUser,HttpStatus.OK);
	}
	
	@DeleteMapping("/{uid}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Integer uid)
	{
		this.userService.deleteUser(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted sucessfully",true),HttpStatus.OK);
		
	}

}



