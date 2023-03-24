package com.week.zumgnmarket.user.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
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