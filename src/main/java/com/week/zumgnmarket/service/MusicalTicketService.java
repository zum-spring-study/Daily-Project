package com.week.zumgnmarket.service;

import com.week.zumgnmarket.entity.Buyer;
import com.week.zumgnmarket.entity.Musical;
import com.week.zumgnmarket.entity.MusicalTicket;
import com.week.zumgnmarket.entity.Purchase;
import com.week.zumgnmarket.repository.MusicalJpaRepository;
import com.week.zumgnmarket.repository.PurchaseJpaRepository;
import com.week.zumgnmarket.repository.TicketJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MusicalTicketService {

    private final TicketJpaRepository ticketJpaRepository;

    private final MusicalJpaRepository musicalJpaRepository;

    private final PurchaseJpaRepository purchaseJpaRepository;

    public MusicalTicket createTicket(MusicalTicket ticket) {
        return ticketJpaRepository.save(ticket);
    }

    public Musical createMusical(Musical musical) {
        return musicalJpaRepository.save(musical);
    }

    public MusicalTicket findTicketByMusical(Musical musical) {
        return ticketJpaRepository.findByMusical(musical);
    }

    public MusicalTicket findTicketById(Integer ticketId) {
        return ticketJpaRepository.findById(ticketId).orElseThrow(() -> new RuntimeException("존재하지 않는 티켓입니다"));
    }
    public Musical findMusicalById(Integer id) {
        return musicalJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 뮤지컬 입니다. "));
    }

    public Purchase buyTicket(Buyer buyer, Integer ticketId) {
        MusicalTicket musicalTicket = findTicketById(ticketId);
        Musical musical = findMusicalById(musicalTicket.getMusicalId());
        if (purchaseJpaRepository.existsByBuyerAndMusical(buyer, musical)) {
            throw new RuntimeException("이미 해당 뮤지컬 티켓을 구매하셨습니다.");
        }
        musicalTicket.decrease();
        return purchaseJpaRepository.save(Purchase.of(buyer, musical));
    }
}
