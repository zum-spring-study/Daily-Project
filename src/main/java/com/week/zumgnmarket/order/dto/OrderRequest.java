package com.week.zumgnmarket.order.dto;

import lombok.Getter;

@Getter
public class OrderRequest {
	public Long userId;
	public Long ticketId;
	public int quantity;

	public OrderRequest(Long userId, Long ticketId, int quantity) {
		this.userId = userId;
		this.ticketId = ticketId;
		this.quantity = quantity;
	}
}
