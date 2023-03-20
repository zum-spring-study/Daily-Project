package com.week.zumgnmarket.ticket.entity;

import static javax.persistence.FetchType.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.week.zumgnmarket.common.domain.BaseEntity;
import com.week.zumgnmarket.order.entity.Order;

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

	private int remainingCount;

	@Builder
	public Ticket(String name, int price, int remainingCount) {
		this.name = name;
		this.price = price;
		this.remainingCount = remainingCount;
	}
}
