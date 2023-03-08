package com.week.zumgnmarket.trade.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class TradeTime {
	@Column(name = "trade_time", columnDefinition = "DATETIME")
	private LocalDateTime time;

	private TradeTime(LocalDateTime time) {
		this.time = time;
	}

	public static TradeTime of(LocalDateTime tradeTime) {
		return new TradeTime(tradeTime);
	}

	public static LocalDateTime toLocalDateTime(String tradeTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.parse(tradeTime, formatter);
	}
}
