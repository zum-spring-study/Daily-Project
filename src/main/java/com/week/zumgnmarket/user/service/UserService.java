package com.week.zumgnmarket.user.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.week.zumgnmarket.user.entity.User;
import com.week.zumgnmarket.user.entity.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public User save(User user){
		return userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public User getUser(Long id) {
		return userRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
	}
}
