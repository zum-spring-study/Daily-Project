package com.week.zumgnmarket.trade.dto;

import lombok.Getter;

@Getter
public class TradeRequest {
	private Long buyerId;
	private Long itemId;
	private String address;
	private String zipCode;
	private String dateTime;

	private TradeRequest(Long buyerId, Long itemId, String address, String zipCode, String dateTime) {
		this.buyerId = buyerId;
		this.itemId = itemId;
		this.address = address;
		this.zipCode = zipCode;
		this.dateTime = dateTime;
	}
}
