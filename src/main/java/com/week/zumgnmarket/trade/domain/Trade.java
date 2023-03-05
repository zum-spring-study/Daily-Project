package com.week.zumgnmarket.trade.domain;

import static javax.persistence.FetchType.*;

import javax.persistence.*;

import com.week.zumgnmarket.common.domain.BaseEntity;
import com.week.zumgnmarket.item.domain.Item;
import com.week.zumgnmarket.user.domain.User;

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

}
