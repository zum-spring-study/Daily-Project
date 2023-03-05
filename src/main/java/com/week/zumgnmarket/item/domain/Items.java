package com.week.zumgnmarket.item.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;


@Embeddable
public class Items {
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	List<Item> items = new ArrayList<>();

}
