package com.week.zumgnmarket.item.exception;

import javax.persistence.EntityNotFoundException;

public class ItemNotFoundException extends EntityNotFoundException {

	public ItemNotFoundException(String message) {
		super(message);
	}
}
