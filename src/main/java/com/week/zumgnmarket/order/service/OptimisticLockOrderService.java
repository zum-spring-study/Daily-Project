package com.week.zumgnmarket.order.service;

import org.springframework.stereotype.Service;

import com.week.zumgnmarket.order.entity.Order;
import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.user.entity.User;

import lombok.RequiredArgsConstructor;

/**
 * Optimistic Lock
 * Lock 이 아니라 Version 을 통해 정합성을 맞추는 방법.
 * 처음에 현재 데이터의 version 을 읽고,
 * 이후 update 하는 시점에서 처음 읽은 버전과 동일한지 확인 후 update 수행.
 * */

@Service
@RequiredArgsConstructor
public class OptimisticLockOrderService implements OrderService{

	@Override
	public Order orderTicket(User user, Ticket ticket, int quantity) {
		ticket.decreaseQuantity(quantity);
		return Order.of(user, ticket, quantity);
	}
}
