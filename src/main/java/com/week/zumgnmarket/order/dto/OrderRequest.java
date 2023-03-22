package com.week.zumgnmarket.order.dto;

import lombok.Getter;

@Getter
public class OrderRequest {
	public Long userId;
	public Long ticketId;
	public int quantity;

}
