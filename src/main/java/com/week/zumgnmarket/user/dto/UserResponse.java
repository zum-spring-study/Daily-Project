package com.week.zumgnmarket.user.dto;

import com.week.zumgnmarket.user.entity.User;

public class UserResponse {
	final String name;
	final String email;

	private UserResponse(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public static UserResponse of(User user) {
		return new UserResponse(user.getName(), user.getUserLogin().getEmail());
	}
}
