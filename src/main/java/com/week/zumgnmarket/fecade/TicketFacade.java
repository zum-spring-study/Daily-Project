package com.week.zumgnmarket.fecade;

import com.week.zumgnmarket.entity.Buyer;
import com.week.zumgnmarket.entity.Musical;
import com.week.zumgnmarket.entity.MusicalTicket;
import com.week.zumgnmarket.entity.Purchase;
import com.week.zumgnmarket.fecade.dto.TicketRequest;
import com.week.zumgnmarket.fecade.dto.TicketResponse;
import com.week.zumgnmarket.service.BuyerService;
import com.week.zumgnmarket.service.MusicalTicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class TicketFacade {
    private final MusicalTicketService musicalTicketService;
    private final BuyerService buyerService;
    private final RedissonClient redissonClient;

    public TicketResponse buy(TicketRequest ticketRequest) {
        Buyer buyer = buyerService.findBuyerById(ticketRequest.getBuyerId());
        Musical musical = musicalTicketService.findMusicalById(ticketRequest.getMusicalId());
        MusicalTicket musicalTicket = musicalTicketService.findTicketByMusical(musical);
        if (!musicalTicket.checkTicketingDate(ticketRequest.getTicketingDate())) {
            throw new RuntimeException("지금은 티켓팅 기간이 아닙니다.");
        }
        RLock lock = redissonClient.getLock(musical.getTitle() + ":" + ticketRequest.getTicketingDate());
        try {
            if (!lock.tryLock(3, 5, TimeUnit.SECONDS)) {
                return new TicketResponse();
            }
            Purchase purchase = musicalTicketService.buyTicket(buyer, musicalTicket.getId());
            return TicketResponse.of(purchase);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //lock.isLocked() && lock.isHeldByCurrentThread()
            if (lock != null && lock.isLocked() && lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public void buyNoLock(TicketRequest ticketRequest) {
        Buyer buyer = buyerService.findBuyerById(ticketRequest.getBuyerId());
        Musical musical = musicalTicketService.findMusicalById(ticketRequest.getMusicalId());
        MusicalTicket musicalTicket = musicalTicketService.findTicketByMusical(musical);
        if (!musicalTicket.checkTicketingDate(ticketRequest.getTicketingDate())) {
            throw new RuntimeException("지금은 티켓팅 기간이 아닙니다.");
        }
        musicalTicketService.buyTicket(buyer, musicalTicket.getId());
    }
}
