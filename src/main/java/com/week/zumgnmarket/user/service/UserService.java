package com.week.zumgnmarket.user.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.week.zumgnmarket.user.entity.User;
import com.week.zumgnmarket.user.entity.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User getUser(Long id) {
		return userRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
	}
}
