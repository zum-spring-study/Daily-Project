package com.week.zumgnmarket.item.domain;

import static javax.persistence.FetchType.*;

import javax.persistence.*;

import com.week.zumgnmarket.city.domain.City;
import com.week.zumgnmarket.common.domain.BaseEntity;
import com.week.zumgnmarket.trade.domain.Trade;
import com.week.zumgnmarket.user.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "item")
public class Item extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Lob
	private String description;

	private int price;

	private String pictureUrl;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "owner_id")
	private User owner;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "city_id")
	private City city;

	@OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "trade_id")
	private Trade trade;

	private boolean sold;


	public Item(String name, String description, int price, String pictureUrl, User owner, City city) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.pictureUrl = pictureUrl;
		this.owner = owner;
		this.city = city;
		this.sold = false;
	}
}
