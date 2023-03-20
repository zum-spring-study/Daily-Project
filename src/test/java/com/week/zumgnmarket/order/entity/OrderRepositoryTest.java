package com.week.zumgnmarket.order.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.ticket.entity.TicketRepository;
import com.week.zumgnmarket.user.entity.User;
import com.week.zumgnmarket.user.entity.UserRepository;

@DataJpaTest
public class OrderRepositoryTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private OrderRepository orderRepository;

	User 회원;
	Ticket 티켓;

	@BeforeEach
	void setup() {
		회원 = new User("kim", "email@email.com", "pwd");
		티켓 = new Ticket("CAT'S", 10000, 1000);
		userRepository.save(회원);
		ticketRepository.save(티켓);
	}

	@Test
	void 연관관계_매핑() {
		//given
		Order 주문 = new Order(회원, 티켓, 1);

		//when
		Order result = orderRepository.save(주문);

		//then
		assertEquals(result.getTicket(), 티켓);
	}
}
