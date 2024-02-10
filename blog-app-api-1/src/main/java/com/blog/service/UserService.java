package com.blog.service;




import java.util.List;

import com.blog.payload.UserDto;

public interface UserService {
	
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user,Integer userId);
	
	UserDto getUserById(Integer userId);
	
	
	
	void deleteUser(Integer userId);
	
	List<UserDto> getAllUser();
	
	
	
	

}
