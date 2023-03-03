package com.week.zumgnmarket.application.dto;

import com.week.zumgnmarket.constant.TownType;
import com.week.zumgnmarket.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private int userId;
    private String nickName;
    private TownType region;

    public static UserResponse of(User user) {
        return UserResponse.builder()
                .userId(user.getId())
                .nickName(user.getNickName())
                .region(TownType.of(user.getTown().getRegion()))
                .build();
    }
}
