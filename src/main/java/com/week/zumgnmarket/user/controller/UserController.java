package com.week.zumgnmarket.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.week.zumgnmarket.user.dto.UserRequest;
import com.week.zumgnmarket.user.dto.UserResponse;
import com.week.zumgnmarket.user.exception.UserNotFoundException;
import com.week.zumgnmarket.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

	@Autowired
	private final UserService userService;

	@PostMapping("/create")
	public ResponseEntity<UserResponse> create(@RequestBody UserRequest request) {
		return ResponseEntity.ok(UserResponse.of(userService.create(request.toUser())));
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserResponse> findUserById(@PathVariable Long userId) {
		return ResponseEntity.ok(UserResponse.of(userService.findUserById(userId)));
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(UserNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
}
