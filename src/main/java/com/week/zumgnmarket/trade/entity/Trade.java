package com.week.zumgnmarket.trade.entity;

import static javax.persistence.FetchType.*;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.week.zumgnmarket.common.domain.BaseEntity;
import com.week.zumgnmarket.item.entity.Item;
import com.week.zumgnmarket.user.entity.User;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "trade")
public class Trade extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "seller_id")
	private User seller;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "buyer_id")
	private User buyer;

	@OneToOne(mappedBy = "trade", fetch = LAZY)
	private Item item;

	@Embedded
	private Address tradeAddress;

	@Embedded
	private TradeTime tradeTime;

	@Enumerated(EnumType.STRING)
	private TradeStatus status;

	@Builder
	private Trade(User seller, User buyer, Item item, Address tradeAddress,
		TradeTime tradeTime, TradeStatus status) {
		this.seller = seller;
		this.buyer = buyer;
		this.item = item;
		this.tradeAddress = tradeAddress;
		this.tradeTime = tradeTime;
		this.status = status;
	}

	public static Trade of(User seller, User buyer, Item item, String address, String zipCode, String tradeTime) {
		return Trade.builder()
			.seller(seller)
			.buyer(buyer)
			.item(item)
			.tradeAddress(Address.of(address, zipCode))
			.tradeTime(TradeTime.of(TradeTime.toLocalDateTime(tradeTime)))
			.build();
	}

	public void cancel() {
		this.status = TradeStatus.CANCEL;
	}

	public void complete() {
		this.item.updateTradeStatus();
		this.status = TradeStatus.TRADED;
	}
}
