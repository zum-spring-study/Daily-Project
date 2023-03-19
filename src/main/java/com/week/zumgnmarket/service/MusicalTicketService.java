package com.week.zumgnmarket.service;

import com.week.zumgnmarket.entity.Buyer;
import com.week.zumgnmarket.entity.Musical;
import com.week.zumgnmarket.entity.MusicalTicket;
import com.week.zumgnmarket.repository.MusicalJpaRepository;
import com.week.zumgnmarket.repository.TicketJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MusicalTicketService {

    private final TicketJpaRepository ticketJpaRepository;

    private final MusicalJpaRepository musicalJpaRepository;

    public MusicalTicket createTicket(MusicalTicket ticket) {
        return ticketJpaRepository.save(ticket);
    }
    @Transactional(readOnly = true)
    public MusicalTicket findTicketByMusical(Musical musical) {
        return ticketJpaRepository.findByMusical(musical);
    }

    public void buyTicket(Buyer buyer, MusicalTicket musicalTicket) {
        Musical musical = musicalTicket.getMusical();
        if (!buyer.isExistMusicals(musical)) {
            throw new RuntimeException("이미 해당 뮤지컬 티켓을 구매하셨습니다. ");
        }
        musicalTicket.decrease();
        buyer.updateMusicals(musicalTicket.getMusical());
    }
    @Transactional(readOnly = true)
    public Musical findMusicalById(Integer id) {
        return musicalJpaRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 뮤지컬 입니다. "));
    }
}
