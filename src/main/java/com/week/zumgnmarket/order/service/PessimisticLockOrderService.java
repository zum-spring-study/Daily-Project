package com.week.zumgnmarket.order.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.week.zumgnmarket.order.entity.Order;
import com.week.zumgnmarket.order.entity.OrderRepository;
import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.user.entity.User;

import lombok.RequiredArgsConstructor;

/**
 * Pessimistic Lock
 * 실제로 데이터에 Lock 을 걸어서 정합성을 맞추는 방법.
 * Lock 이 해제되기 전에는 다른 트랜잭션에서 데이터를 가져갈 수 없다.
 * */

@Service
@Transactional
@RequiredArgsConstructor
public class PessimisticLockOrderService implements OrderService {

	private final OrderRepository orderRepository;

	@Override
	public Order orderTicket(User user, Ticket ticket, int quantity) {
		ticket.decreaseQuantity(quantity);
		return orderRepository.save(Order.of(user, ticket, quantity));
	}

}
