package com.week.zumgnmarket.fecade;

import com.week.zumgnmarket.entity.Buyer;
import com.week.zumgnmarket.entity.Musical;
import com.week.zumgnmarket.entity.MusicalTicket;
import com.week.zumgnmarket.fecade.dto.TicketRequest;
import com.week.zumgnmarket.repository.BuyerJpaRepository;
import com.week.zumgnmarket.repository.MusicalJpaRepository;
import com.week.zumgnmarket.repository.PurchaseJpaRepository;
import com.week.zumgnmarket.repository.TicketJpaRepository;
import com.week.zumgnmarket.service.MusicalTicketService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class TicketFacadeTest {

    @Autowired
    private TicketFacade ticketFacade;

    @Autowired
    private MusicalTicketService musicalTicketService;

    @Autowired
    private  MusicalJpaRepository musicalJpaRepository;

    @Autowired
    private  TicketJpaRepository ticketJpaRepository;

    @Autowired
    private BuyerJpaRepository buyerJpaRepository;

    @Autowired
    private PurchaseJpaRepository purchaseJpaRepository;

    private static final Long TOTAL_AMOUNT = 100L;
    private static final Long SOLD_OUT = 0L;

    private MusicalTicket musicalTicket_;

    private static List<Buyer> buyers = new ArrayList<>();

    @BeforeEach
    void 사용자_티켓_셋팅() {

        for (int i=0; i<150; i++) {
            Buyer buyer = Buyer.of("김"+i+"씨");
            buyers.add(buyerJpaRepository.save(buyer));
        }

        Musical musical = Musical.of("캣츠", "1000만 돌파 !!");
        musicalTicketService.createMusical(musical);
        MusicalTicket musicalTicket = MusicalTicket.of(musical, TOTAL_AMOUNT, LocalDate.now());
        musicalTicket_ = musicalTicketService.createTicket(musicalTicket);
    }

    @AfterEach
    void 사용자_티켓_초기화() {
        for (int i = 0; i<150; i++) {
            buyers.clear();
        }
        purchaseJpaRepository.deleteAll();
        buyerJpaRepository.deleteAll();
        ticketJpaRepository.deleteAll();
        musicalJpaRepository.deleteAll();
    }

    @Test
    @Order(1)
    void 락0_150명이_티켓팅을_실행() throws InterruptedException {
        int people = 150;
        CountDownLatch countDownLatch = new CountDownLatch(people);
        List<Thread> threads = buyers.stream()
                .map(buyer -> {
                    TicketRequest ticketRequest = TicketRequest.of(buyer.getId(), musicalTicket_.getMusicalId(), LocalDate.now());
                    return new Thread(new Buying(ticketRequest, countDownLatch));
                })
                .limit(people).collect(Collectors.toList());
        threads.forEach(Thread::start);
        countDownLatch.await();
        MusicalTicket musicalTicket = musicalTicketService.findTicketById(musicalTicket_.getId());
        assertEquals(musicalTicket.getTicketCount(), SOLD_OUT);
    }

    @Test
    @Order(1)
    void 락X_150명이_티켓팅을_실행() throws InterruptedException {
        int people = 150;
        CountDownLatch countDownLatch = new CountDownLatch(people);
        List<Thread> threads = buyers.stream()
                .map(buyer -> {
                    TicketRequest ticketRequest = TicketRequest.of(buyer.getId(), musicalTicket_.getMusicalId(), LocalDate.now());
                    return new Thread(new BuyingNoLock(ticketRequest, countDownLatch));
                })
                .limit(people).collect(Collectors.toList());
        threads.forEach(Thread::start);
        countDownLatch.await();
        MusicalTicket musicalTicket = musicalTicketService.findTicketById(musicalTicket_.getId());
        assertNotEquals(musicalTicket.getTicketCount(), SOLD_OUT);
    }

    private class Buying implements Runnable {

        private TicketRequest ticketRequest;
        private CountDownLatch countDownLatch;

        public Buying(TicketRequest ticketRequest, CountDownLatch countDownLatch) {
            this.ticketRequest = ticketRequest;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            ticketFacade.buy(ticketRequest);
            countDownLatch.countDown();
        }
    }

    private class BuyingNoLock implements Runnable {

        private TicketRequest ticketRequest;
        private CountDownLatch countDownLatch;

        public BuyingNoLock(TicketRequest ticketRequest, CountDownLatch countDownLatch) {
            this.ticketRequest = ticketRequest;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            ticketFacade.buyNoLock(ticketRequest);
            countDownLatch.countDown();
        }
    }
}