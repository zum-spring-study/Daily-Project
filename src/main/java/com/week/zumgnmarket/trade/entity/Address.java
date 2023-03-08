package com.week.zumgnmarket.trade.entity;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class Address {
	private String address;
	private String zipCode;

	private Address(String address, String zipCode) {
		this.address = address;
		this.zipCode = zipCode;
	}

	public static Address of(String address, String zipCode) {
		return new Address(address, zipCode);
	}
}
