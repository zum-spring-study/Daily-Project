package com.week.zumgnmarket.user.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof UserLogin))
			return false;
		UserLogin userLogin = (UserLogin)o;
		return email.equals(userLogin.email) && password.equals(userLogin.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}
}
