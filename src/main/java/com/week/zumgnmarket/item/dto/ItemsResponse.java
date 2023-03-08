package com.week.zumgnmarket.item.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.week.zumgnmarket.item.entity.Item;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemsResponse {
	private List<ItemResponse> itemsResponse;

	public static ItemsResponse of(List<Item> items) {
		return ItemsResponse.builder()
			.itemsResponse(items.stream()
				.map(ItemResponse::of)
				.collect(Collectors.toList()))
			.build();
	}
}
