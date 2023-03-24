package com.week.zumgnmarket.order.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.week.zumgnmarket.order.dto.OrderRequest;
import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.ticket.service.TicketService;
import com.week.zumgnmarket.user.entity.User;
import com.week.zumgnmarket.user.service.UserService;

@SpringBootTest
public class OrderFacadeTest {
	@Autowired
	private UserService userService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private OrderFacade orderFacade;

	/**
	 * 학습 테스트)
	 * 해당 엔티티가 트랜잭션에 따라 변경 감지가 일어나는 조건을 테스트.
	 * 만약 orderFacade 에 @Transactional 이 없다면 해당 테스트는 실패합니다.
	 * 또한 static '티켓'은 order 의 Ticket 과 다른 트랜잭션, 영속성 컨텍스트에 존재하기 때문에 변경감지가 발생하지 않습니다.
	 * */
	@Test
	void 영속성_컨텍스트_변경감지_테스트() {
		//given
		User 회원 = new User("kim", "email@email.com", "pwd");
		Ticket 티켓 = new Ticket("CAT'S", 10000, 1000);
		userService.save(회원);
		ticketService.save(티켓);

		//when
		orderFacade.order(new OrderRequest(회원.getId(), 티켓.getId(), 1));

		//then
		Ticket result = ticketService.getTicket(티켓.getId());
		assertEquals(result.getQuantity(), 999);
		assertNotEquals(티켓.getQuantity(), 999);
	}
}
