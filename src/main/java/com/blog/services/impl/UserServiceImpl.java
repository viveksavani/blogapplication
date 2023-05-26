package com.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.UserDto;
import com.blog.repositories.UserRepo;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo repo;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = dtoToUser(userDto);
		
		User savedUser = repo.save(user);
		
		return userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user  = repo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		
		User updateUser = repo.save(user);
		
		return userToDto(updateUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
	
		User user  = repo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		return userToDto(user);
	}

	@Override
	public List<UserDto> getAlluser() {
		
		List<User> users = repo.findAll();
		
		List<UserDto> userDto = users.stream().map(user->userToDto(user)).collect(Collectors.toList());
		
		return userDto;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = repo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		repo.delete(user);
	}
	
	private User dtoToUser(UserDto userDto) {
		User user = new User();
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setId(userDto.getId());
		return user;
	}
	
	
	private UserDto userToDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setAbout(user.getAbout());
		userDto.setEmail(user.getEmail());
		userDto.setName(user.getName());
		userDto.setPassword(user.getPassword());
		userDto.setId(user.getId());
		return userDto;
	}

}
