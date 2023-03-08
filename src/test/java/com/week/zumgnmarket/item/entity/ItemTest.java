package com.week.zumgnmarket.item.entity;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.week.zumgnmarket.city.entity.CityTest;
import com.week.zumgnmarket.user.entity.UserTest;

@DataJpaTest
public class ItemTest {

	public static Item 연필 = Item.builder()
		.name("연필")
		.description("중고 물품입니다.")
		.price(1000)
		.pictureUrl("https://")
		.owner(UserTest.동네_주민)
		.city(CityTest.서울)
		.build();

	public static Item 공책 = Item.builder()
		.name("공책")
		.description("중고 물품입니다.")
		.price(1500)
		.pictureUrl("https://")
		.owner(UserTest.동네_주민)
		.city(CityTest.서울)
		.build();

	public static Item 커피쿠폰 = Item.builder()
		.name("커피쿠폰")
		.description("아메리카노 쿠폰입니다.")
		.price(3000)
		.pictureUrl("https://")
		.owner(UserTest.신규_주민)
		.city(CityTest.경기도)
		.build();

}
