package com.week.zumgnmarket.user.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.week.zumgnmarket.user.domain.User;
import com.week.zumgnmarket.user.domain.UserTest;

@SpringBootTest
@Transactional
public class UserServiceTest {
	@Autowired
	private UserService userService;

	@Test
	void 유저_가입() {
		User saved = userService.create(UserTest.동네_주민);
		assertEquals(UserTest.동네_주민.getUserLogin(), saved.getUserLogin());
	}

	@Test
	void 유저_조회() {
		User saved = userService.create(UserTest.동네_주민);
		User found = userService.findUserById(saved.getId());
		assertEquals(saved.getUserLogin(), found.getUserLogin());
	}
}
