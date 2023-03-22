package com.week.zumgnmarket.order.dto;

import com.week.zumgnmarket.order.entity.Order;
import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.user.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OrderResponse {
	public Long orderId;
	public Long userId;
	public Long ticketId;
	public int quantity;

	@Builder
	public OrderResponse(Long orderId, Long userId, Long ticketId, int quantity) {
		this.orderId = orderId;
		this.userId = userId;
		this.ticketId = ticketId;
		this.quantity = quantity;
	}

	public static OrderResponse of(Order order, User user, Ticket ticket) {
		return OrderResponse.builder()
			.orderId(order.getId())
			.userId(user.getId())
			.ticketId(ticket.getId())
			.quantity(order.getTicketCount())
			.build();
	}
}
