package com.week.zumgnmarket.trade.domain;

import javax.persistence.Embeddable;

import lombok.Getter;

@Getter
@Embeddable
public class Address {
	private String address;
	private String zipCode;

}
