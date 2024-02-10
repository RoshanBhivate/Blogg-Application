package com.blog.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.payload.UserDto;
import com.blog.repository.UserRepo;
import com.blog.service.UserService;
import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;


	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User save = this.userRepo.save(user);
		return this.userToDto(save);
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
	
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		return this.userToDto(user);
	}
	
	
	@Override
	public List<UserDto> getAllUser() {
		List<User> users = this.userRepo.findAll();

		List<UserDto> userDto = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

		this.userRepo.delete(user);

		
	}

	

	//@Autowired
	//private ModelMapper modelMapper;
	
	
	private User dtoToUser(UserDto userDto) {
//		User user=new User();
//		user. setId(userDto. getId( ) ) ;
//		user. setName( userDto. getName( ) ) ;
//		user. setEmail(userDto. getEmail ( ) ) ;
//		user. setAbout( userDto. getAbout ( ) ) ;
//		user. setPassword (userDto. getPassword( ) ) ;
		
		User user=this.modelMapper.map(userDto, User.class);
		return user;
	}
	
	public UserDto userToDto(User user) {
//		UserDto userDto=new UserDto();
//		userDto. setId( user.getId( ) );
//		userDto.setName(user.getName());
//		userDto. setEmail(user.getEmail()) ;
//		userDto. setPassword (user.getPassword());
//		userDto. setAbout(user.getAbout( ));
		
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;
	}
	

}
