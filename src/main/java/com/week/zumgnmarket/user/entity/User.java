package com.week.zumgnmarket.user.entity;

import javax.persistence.*;

import com.week.zumgnmarket.common.domain.BaseEntity;
import com.week.zumgnmarket.order.entity.Orders;

import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Embedded
	private UserLogin userLogin;

	@Embedded
	private Orders orders;

	@Builder
	public User(String name, String email, String password) {
		this.name = name;
		this.userLogin = new UserLogin(email, password);
	}

}