package com.week.zumgnmarket.item.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSearchCondition {
	Long userId;

	Long cityId;

	String keyword;


	public ItemSearchCondition(Long userId, Long cityId, String keyword) {
		this.userId = userId;
		this.cityId = cityId;
		this.keyword = keyword;
	}


	public static ItemSearchCondition of(Long userId, Long cityId, String keyword) {
		return new ItemSearchCondition(userId, cityId, keyword);
	}
}
