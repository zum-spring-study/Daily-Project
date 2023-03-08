package com.week.zumgnmarket.item.entity;

import static javax.persistence.FetchType.*;

import java.util.Objects;

import javax.persistence.*;

import com.week.zumgnmarket.city.entity.City;
import com.week.zumgnmarket.common.domain.BaseEntity;
import com.week.zumgnmarket.trade.entity.Trade;
import com.week.zumgnmarket.user.entity.User;

import lombok.*;

@Entity
@Getter
@ToString
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

	@Builder
	public Item(String name, String description, int price, String pictureUrl, User owner, City city) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.pictureUrl = pictureUrl;
		this.owner = owner;
		this.city = city;
		this.sold = false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Item)) return false;
		Item item = (Item)o;
		return getPrice() == item.getPrice() && isSold() == item.isSold() && Objects.equals(getId(), item.getId())
			&& Objects.equals(getName(), item.getName()) && Objects.equals(getDescription(),
			item.getDescription()) && Objects.equals(getPictureUrl(), item.getPictureUrl())
			&& Objects.equals(getOwner(), item.getOwner()) && Objects.equals(getCity(), item.getCity())
			&& Objects.equals(getTrade(), item.getTrade());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), getDescription(), getPrice(), getPictureUrl(), getOwner(), getCity(), getTrade(),
			isSold());
	}
}
