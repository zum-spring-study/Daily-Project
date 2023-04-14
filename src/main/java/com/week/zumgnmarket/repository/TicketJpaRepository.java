package com.week.zumgnmarket.repository;

import com.week.zumgnmarket.entity.Musical;
import com.week.zumgnmarket.entity.MusicalTicket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketJpaRepository extends JpaRepository<MusicalTicket, Integer> {
    MusicalTicket findByMusical(Musical musical);
}
