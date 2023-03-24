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

import com.week.zumgnmarket.order.entity.Order;
import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.ticket.entity.TicketRepository;
import com.week.zumgnmarket.user.entity.User;
import com.week.zumgnmarket.user.entity.UserRepository;

@SpringBootTest
public class OrderServiceTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private OrderService orderService;

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
	void 티켓_주문() {
		//given
		int orderQuantity = 2;
		int remainingQuantity = 티켓.getQuantity();

		// when
		Order 주문 = orderService.orderTicket(회원, 티켓, orderQuantity);

		//then
		assertEquals(티켓.getQuantity(), remainingQuantity - orderQuantity);
	}

	@Test
	void 동시에_티켓_주문_실패() throws InterruptedException {
		//given
		ExecutorService executorService = Executors.newFixedThreadPool(123);
		CountDownLatch countDownLatch = new CountDownLatch(thread);

		//when
		for (int i = 0; i < thread; i++) {
			executorService.execute(() -> {
				orderService.orderTicket(회원, 티켓, 1);
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();

		//then
		assertNotEquals(0, 티켓.getQuantity());
	}
}
