package com.week.zumgnmarket.service;

import com.week.zumgnmarket.application.dto.UserRequest;
import com.week.zumgnmarket.application.dto.UserResponse;
import com.week.zumgnmarket.domain.Town;
import com.week.zumgnmarket.domain.User;
import com.week.zumgnmarket.repository.TownRepository;
import com.week.zumgnmarket.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userRepository;
    private final TownRepository townRepository;

    public UserResponse saveUser(UserRequest userDto) {
        if (checkNickNameDuplicate(userDto.getNickName())) {
            throw new DuplicateFormatFlagsException("이미 사용중인 아이디입니다.");
        }
        Town town = townRepository.findByRegion(userDto.getTown().getRegion());
        User user = User.of(userDto, town);
        userRepository.save(user);
        UserResponse userResponse = findByNickName(userDto.getNickName());
        return userResponse;
    }

    private UserResponse findByNickName(String nickName) {
        return UserResponse.of(userRepository.findByNickName(nickName));
    }


    private boolean checkNickNameDuplicate(String nickName) {
        return userRepository.existsByNickName(nickName);
    }
}
