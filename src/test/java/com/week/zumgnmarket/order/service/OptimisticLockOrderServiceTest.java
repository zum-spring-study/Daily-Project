package com.week.zumgnmarket.order.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.week.zumgnmarket.order.controller.OrderFacade;
import com.week.zumgnmarket.order.dto.OrderRequest;
import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.ticket.entity.TicketRepository;
import com.week.zumgnmarket.ticket.service.TicketService;
import com.week.zumgnmarket.user.entity.User;
import com.week.zumgnmarket.user.entity.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class OptimisticLockOrderServiceTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private OrderFacade orderFacade;

	private User 회원;
	private Ticket 티켓;
	private final int thread = 1000;

	@BeforeEach
	void setup() {
		회원 = new User("kim", "email@email.com", "pwd");
		티켓 = new Ticket("CAT'S", 10000, 1000);
		userRepository.save(회원);
		ticketRepository.save(티켓);
	}

	@AfterEach
	void after() {
		userRepository.deleteAll();
		ticketRepository.deleteAll();
	}

	@Test
	void 동시에_티켓_주문() throws InterruptedException {
		//given
		ExecutorService executorService = Executors.newFixedThreadPool(123);
		CountDownLatch countDownLatch = new CountDownLatch(thread);

		//when
		for (int i = 0; i < thread; i++) {
			executorService.execute(() -> {
				try {
					orderFacade.orderWithOptimisticLock(new OrderRequest(회원.getId(), 티켓.getId(), 1));
					log.info("남은 티켓 수량 : " + ticketService.getTicket(티켓.getId()).getQuantity());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();

		//then
		Ticket result = ticketService.getTicket(티켓.getId());
		assertEquals(result.getQuantity(), 0);
	}
}
