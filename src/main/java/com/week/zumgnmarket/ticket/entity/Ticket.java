package com.week.zumgnmarket.ticket.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.week.zumgnmarket.common.domain.BaseEntity;
import com.week.zumgnmarket.ticket.exception.NotEnoughTicketException;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tickets")
public class Ticket extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private int price;

	private int quantity;

	@Builder
	public Ticket(String name, int price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public void decreaseQuantity(int orderQuantity) {
		checkQuantity(orderQuantity);
		this.quantity -= orderQuantity;
	}

	private void checkQuantity(int orderQuantity) {
		if (quantity == 0 || quantity < orderQuantity) {
			throw new NotEnoughTicketException();
		}
	}
}
