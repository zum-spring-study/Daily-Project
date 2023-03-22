package com.week.zumgnmarket.ticket.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.ticket.entity.TicketQueryDslRepository;
import com.week.zumgnmarket.ticket.entity.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {

	private final TicketRepository ticketRepository;

	private final TicketQueryDslRepository ticketQueryDslRepository;

	@Transactional(readOnly = true)
	public Ticket getTicket(Long id) {
		return ticketRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + id));
	}

	@Transactional(readOnly = true)
	public Ticket getTicketWithLock(Long id) {
		return ticketQueryDslRepository.findByIdWithLock(id)
			.orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + id));
	}
}
