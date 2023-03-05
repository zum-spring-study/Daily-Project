package com.week.zumgnmarket.user.domain.dto;

import com.week.zumgnmarket.user.domain.User;

import lombok.Getter;

@Getter
public class UserRequest {
	private String name;
	private String email;
	private String password;

	private UserRequest(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public static UserRequest create(String name, String email, String password) {
		return new UserRequest(name, email, password);
	}

	public User toUser() {
		return new User(name, email, password);
	}
}
