package com.week.zumgnmarket.user.domain.dto;

import com.week.zumgnmarket.user.domain.User;

public class UserResponse {
	final String name;
	final String email;

	private UserResponse(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public static UserResponse create(User user) {
		return new UserResponse(user.getName(), user.getUserLogin().getEmail());
	}
}
