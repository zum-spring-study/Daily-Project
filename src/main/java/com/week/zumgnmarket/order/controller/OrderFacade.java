package com.week.zumgnmarket.order.controller;

import org.springframework.stereotype.Service;

import com.week.zumgnmarket.order.dto.OrderRequest;
import com.week.zumgnmarket.order.dto.OrderResponse;
import com.week.zumgnmarket.order.entity.Order;
import com.week.zumgnmarket.order.service.OrderService;
import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.ticket.service.TicketService;
import com.week.zumgnmarket.user.entity.User;
import com.week.zumgnmarket.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderFacade {

	private final UserService userService;
	private final TicketService ticketService;
	private final OrderService orderService;

	public OrderResponse order(OrderRequest request) {
		User user = userService.getUser(request.getUserId());
		Ticket ticket = ticketService.getTicket(request.getTicketId());
		Order order = orderService.orderTicket(user, ticket, request.getQuantity());

		return OrderResponse.of(order, user, ticket);
	}
}
