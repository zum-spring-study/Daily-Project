package com.week.zumgnmarket.user.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserTest {
	public static User 동네_주민 = new User("이름", "email@email.com", "pwd");
	public static User 신규_주민 = new User("이름2", "email2@email.com", "pwd2");

	@Autowired
	UserRepository userRepository;

	public void 사용자_목록_저장() {
		동네_주민 = userRepository.save(동네_주민);
		신규_주민 = userRepository.save(신규_주민);
	}
}
