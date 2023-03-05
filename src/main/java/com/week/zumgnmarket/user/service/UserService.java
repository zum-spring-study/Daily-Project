package com.week.zumgnmarket.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.week.zumgnmarket.user.domain.User;
import com.week.zumgnmarket.user.domain.UserRepository;
import com.week.zumgnmarket.user.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User create(User user) {
		return userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public User findUserById(Long id) {
		return userRepository.findById(id)
			.orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
	}

}
