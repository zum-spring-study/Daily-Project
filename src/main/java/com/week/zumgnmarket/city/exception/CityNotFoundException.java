package com.week.zumgnmarket.city.exception;

import javax.persistence.EntityNotFoundException;

public class CityNotFoundException extends EntityNotFoundException {

	public CityNotFoundException(String message) {
		super(message);
	}
}
