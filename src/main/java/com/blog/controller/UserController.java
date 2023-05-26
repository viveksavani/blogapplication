package com.blog.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.blog.payloads.ApiResponse;
import com.blog.payloads.UserDto;
import com.blog.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	UserService service;

	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		UserDto createUser = service.createUser(userDto);

		return new ResponseEntity<UserDto>(createUser, HttpStatus.CREATED);

	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId) {

		UserDto updateUser = service.updateUser(userDto, userId);

		return ResponseEntity.ok(updateUser);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId) {

		UserDto user = service.getUserById(userId);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser() {

		List<UserDto> alluser = service.getAlluser();

		return new ResponseEntity<List<UserDto>>(alluser, HttpStatus.OK);

	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) {

		service.deleteUser(userId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Delete Successfully", true,new Date()),HttpStatus.OK);

	}

}
