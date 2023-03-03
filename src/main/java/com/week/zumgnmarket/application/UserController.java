package com.week.zumgnmarket.application;

import com.week.zumgnmarket.application.dto.UserRequest;
import com.week.zumgnmarket.application.dto.UserResponse;
import com.week.zumgnmarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/save")
    public UserResponse saveUser(@RequestBody UserRequest userDto) {
        return userService.saveUser(userDto);
    }
}
