package com.week.zumgnmarket.order.service;

import org.springframework.stereotype.Service;

import com.week.zumgnmarket.order.entity.Order;
import com.week.zumgnmarket.order.entity.OrderRepository;
import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.user.entity.User;

import lombok.RequiredArgsConstructor;

/**
 * synchronized
 * 문제) 서버가 한 대일 경우 문제가 없지만
 * synchronized 는 각 프로세스 안에서만 보장이 되기 때문에
 * 여러 서버 스레드에서 접근을 하게 된다면 race condition 이 발생할 수 있다.
 * */

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