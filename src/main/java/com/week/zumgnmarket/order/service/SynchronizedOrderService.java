package com.week.zumgnmarket.order.service;

import org.springframework.stereotype.Service;

import com.week.zumgnmarket.order.entity.Order;
import com.week.zumgnmarket.order.entity.OrderRepository;
import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SynchronizedOrderService implements OrderService {

	private final OrderRepository orderRepository;

	@Override
	public synchronized Order orderTicket(User user, Ticket ticket, int quantity) {
		ticket.decreaseQuantity(quantity);
		return orderRepository.save(Order.of(user, ticket, quantity));
	}
}