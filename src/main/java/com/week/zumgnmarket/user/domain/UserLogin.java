package com.week.zumgnmarket.user.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserLogin {

	@Column(length = 50, nullable = false)
	private String email;

	@Column(length = 20, nullable = false)
	private String password;


	public UserLogin(String email, String password) {
		this.email = email;
		this.password = password;
	}
}
