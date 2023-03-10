package com.week.zumgnmarket.application.dto;

import com.week.zumgnmarket.constant.TownType;
import lombok.Getter;

@Getter
public class UserRequest {
    private String nickName;
    private TownType town;
}
