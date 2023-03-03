package com.week.zumgnmarket.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * 3개의 지역만 있다고 가정합니다 ^a^ ... 넘 많음 ㅎ
 */
@Getter
@AllArgsConstructor
public enum TownType {
    NOWON("노원구"),
    DOBONG("도봉구"),
    YONGSAN("용산구");

    private String region;
    public static TownType of(String region) {
        return Arrays.stream(TownType.values()).filter(data -> data.getRegion().equals(region))
                .findFirst().orElseThrow();
    }
}
