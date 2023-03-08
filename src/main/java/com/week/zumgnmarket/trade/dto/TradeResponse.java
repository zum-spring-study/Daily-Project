package com.week.zumgnmarket.trade.dto;

import com.week.zumgnmarket.item.dto.ItemResponse;
import com.week.zumgnmarket.trade.entity.Trade;
import com.week.zumgnmarket.user.dto.UserResponse;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TradeResponse {
	private UserResponse seller;
	private UserResponse buyer;
	private ItemResponse item;
	private String address;
	private String tradeTime;
	private String status;

	@Builder
	public TradeResponse(UserResponse seller, UserResponse buyer, ItemResponse item, String address,
		String tradeTime, String status) {
		this.seller = seller;
		this.buyer = buyer;
		this.item = item;
		this.address = address;
		this.tradeTime = tradeTime;
		this.status = status;
	}

	public static TradeResponse of(Trade trade) {
		return TradeResponse.builder()
			.seller(UserResponse.of(trade.getSeller()))
			.buyer(UserResponse.of(trade.getBuyer()))
			.item(ItemResponse.of(trade.getItem()))
			.address(trade.getTradeAddress().toString())
			.tradeTime(trade.getTradeTime().toString())
			.status(trade.getStatus().toString())
			.build();
	}
}
