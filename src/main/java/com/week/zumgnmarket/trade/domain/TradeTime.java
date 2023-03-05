package com.week.zumgnmarket.trade.domain;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;

@Embeddable
public class TradeTime {
	private LocalDateTime time;
}
