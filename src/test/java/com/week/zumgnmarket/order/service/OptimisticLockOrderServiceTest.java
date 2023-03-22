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
import com.week.zumgnmarket.user.entity.User;
import com.week.zumgnmarket.user.entity.UserRepository;

@SpringBootTest
public class OptimisticLockOrderServiceTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TicketRepository ticketRepository;
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
				//TODO: 미해결 - 변경감지가 일어나지 않는 이슈
				try {
					orderFacade.orderWithOptimisticLock(new OrderRequest(회원.getId(), 티켓.getId(), 1));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();

		//then
		assertEquals(0, 티켓.getQuantity());
	}
}
