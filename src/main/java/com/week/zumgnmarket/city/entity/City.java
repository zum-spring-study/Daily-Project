package com.week.zumgnmarket.city.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.week.zumgnmarket.common.domain.BaseEntity;
import com.week.zumgnmarket.item.entity.Item;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class City extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String name;

	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<Item> items = new ArrayList<>();

	public City(String name) {
		this.name = name;
	}
}
