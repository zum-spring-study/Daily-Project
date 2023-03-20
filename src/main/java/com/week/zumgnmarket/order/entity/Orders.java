package com.week.zumgnmarket.order.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;

import lombok.Getter;

@Getter
@Embeddable
public class Orders {
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	List<Order> orders = new ArrayList<>();

}
