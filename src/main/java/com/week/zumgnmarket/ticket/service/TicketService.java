package com.week.zumgnmarket.ticket.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.ticket.entity.TicketQueryDslRepository;
import com.week.zumgnmarket.ticket.entity.TicketRepository;
import com.week.zumgnmarket.ticket.exception.ErrorMessage;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {

	private final TicketRepository ticketRepository;

	private final TicketQueryDslRepository ticketQueryDslRepository;

	public Ticket save(Ticket ticket){
		return ticketRepository.save(ticket);
	}

	@Transactional(readOnly = true)
	public Ticket getTicket(Long id) {
		return ticketRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException(ErrorMessage.TICKET_NOT_FOUND_BY_ID + id));
	}

	@Transactional(readOnly = true)
	public Ticket getTicketWithPessimisticLock(Long id) {
		return ticketQueryDslRepository.findByIdWithPessimisticLock(id)
			.orElseThrow(() -> new EntityNotFoundException(ErrorMessage.TICKET_NOT_FOUND_BY_ID + id));
	}

	@Transactional(readOnly = true)
	public Ticket getTicketWithOptimisticLock(Long id) {
		return ticketQueryDslRepository.findByIdWithOptimisticLock(id)
			.orElseThrow(() -> new EntityNotFoundException(ErrorMessage.TICKET_NOT_FOUND_BY_ID + id));
	}
}
