package com.week.zumgnmarket.fecade;

import com.week.zumgnmarket.entity.Buyer;
import com.week.zumgnmarket.entity.Musical;
import com.week.zumgnmarket.entity.MusicalTicket;
import com.week.zumgnmarket.fecade.dto.TicketRequest;
import com.week.zumgnmarket.service.BuyerService;
import com.week.zumgnmarket.service.MusicalTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketFacade {
    private final MusicalTicketService musicalTicketService;
    private final BuyerService buyerService;

    public void buyTicket(TicketRequest ticketRequest) {
        Buyer buyer = buyerService.findBuyerById(ticketRequest.getBuyerId());
        Musical musical = musicalTicketService.findMusicalById(ticketRequest.getMusicalId());
        MusicalTicket musicalTicket = musicalTicketService.findTicketByMusical(musical);
        if (!musicalTicket.checkTicketingDate(ticketRequest.getTicketingDate())) {
            throw new RuntimeException("지금은 티켓팅 기간이 아닙니다.");
        }
        musicalTicketService.buyTicket(buyer, musicalTicket);
    }
}
