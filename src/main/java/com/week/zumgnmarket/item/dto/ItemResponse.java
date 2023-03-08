package com.week.zumgnmarket.item.dto;

import com.week.zumgnmarket.item.entity.Item;
import com.week.zumgnmarket.user.dto.UserResponse;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemResponse {
	private String name;
	private String description;
	private int price;
	private String pictureUrl;
	private Long cityId;
	private UserResponse owner;

	@Builder
	private ItemResponse(String name, String description, int price, String pictureUrl, Long cityId,
		UserResponse owner) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.pictureUrl = pictureUrl;
		this.cityId = cityId;
		this.owner = owner;
	}

	public static ItemResponse of(Item item) {
		return ItemResponse.builder()
			.name(item.getName())
			.description(item.getDescription())
			.price(item.getPrice())
			.pictureUrl(item.getPictureUrl())
			.owner(UserResponse.of(item.getOwner()))
			.build();
	}
}
