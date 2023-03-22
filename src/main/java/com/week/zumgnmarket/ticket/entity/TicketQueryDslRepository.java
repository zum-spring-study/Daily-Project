package com.week.zumgnmarket.ticket.entity;

import static com.week.zumgnmarket.ticket.entity.QTicket.*;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TicketQueryDslRepository {

	private final JPAQueryFactory queryFactory;

	@Lock(value = LockModeType.PESSIMISTIC_WRITE)
	public Optional<Ticket> findByIdWithLock(Long id) {
		return Optional.ofNullable(queryFactory.selectFrom(ticket)
			.where(ticket.id.eq(id))
			.fetchOne());
	}

}
