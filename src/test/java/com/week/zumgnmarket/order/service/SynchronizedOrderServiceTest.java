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

import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.ticket.entity.TicketRepository;
import com.week.zumgnmarket.user.entity.User;
import com.week.zumgnmarket.user.entity.UserRepository;

@SpringBootTest
public class SynchronizedOrderServiceTest {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TicketRepository ticketRepository;
	@Autowired
	private SynchronizedOrderService orderService;

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

	/**
	 * 문제)
	 * 서버가 한 대일 경우 문제가 없지만
	 * synchronized 는 각 프로세스 안에서만 보장이 되기 때문에
	 * 여러 서버 스레드에서 접근을 하게 된다면 race condition 이 발생할 수 있습니다.
	 * */
	@Test
	void 동시에_티켓_주문() throws InterruptedException {
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
		assertEquals(0, 티켓.getQuantity());
	}
}
