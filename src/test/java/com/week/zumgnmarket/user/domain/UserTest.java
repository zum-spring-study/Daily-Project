package com.week.zumgnmarket.user.domain;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserTest {
	public static final User 동네_주민 = new User("이름", "email@email.com", "pwd");

}
