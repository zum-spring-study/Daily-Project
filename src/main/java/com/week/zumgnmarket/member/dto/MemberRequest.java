package com.week.zumgnmarket.member.dto;

import com.week.zumgnmarket.enums.LocationNames;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    private String name;
    private LocationNames location;

}
