package com.week.zumgnmarket.ticket.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.week.zumgnmarket.ticket.entity.Ticket;
import com.week.zumgnmarket.ticket.entity.TicketRepository;

@Service
@Transactional
public class TicketService {

	private TicketRepository ticketRepository;

	@Transactional(readOnly = true)
	public Ticket getTicket(Long id) {
		return ticketRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Ticket not found with id: " + id));
	}
}
