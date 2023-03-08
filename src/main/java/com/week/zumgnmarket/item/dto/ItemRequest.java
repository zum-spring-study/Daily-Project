package com.week.zumgnmarket.item.dto;

import lombok.Getter;

@Getter
public class ItemRequest {
	private String name;
	private String description;
	private int price;
	private String pictureUrl;
	private Long userId;
	private Long cityId;


	public ItemRequest(String name, String description, int price, String pictureUrl, Long userId, Long cityId) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.pictureUrl = pictureUrl;
		this.userId = userId;
		this.cityId = cityId;
	}
}
