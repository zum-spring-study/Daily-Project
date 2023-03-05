package com.week.zumgnmarket.user.domain;

import javax.persistence.*;

import com.week.zumgnmarket.common.domain.BaseEntity;
import com.week.zumgnmarket.item.domain.Items;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "zum_user")
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Embedded
	private UserLogin userLogin;

	@Embedded
	private Items items;

	public User(String name, String email, String password) {
		this.name = name;
		this.userLogin = new UserLogin(email, password);
	}
}