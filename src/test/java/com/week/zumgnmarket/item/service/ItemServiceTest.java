package com.week.zumgnmarket.item.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.week.zumgnmarket.city.entity.CityRepository;
import com.week.zumgnmarket.city.entity.CityTest;
import com.week.zumgnmarket.item.dto.ItemSearchCondition;
import com.week.zumgnmarket.item.entity.Item;
import com.week.zumgnmarket.item.entity.ItemRepository;
import com.week.zumgnmarket.item.entity.ItemTest;
import com.week.zumgnmarket.item.exception.ItemNotFoundException;
import com.week.zumgnmarket.user.entity.UserRepository;
import com.week.zumgnmarket.user.entity.UserTest;

@SpringBootTest
@Transactional
public class ItemServiceTest {

	@Autowired
	ItemService itemService;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CityRepository cityRepository;

	/**
	 * ERROR:
	 * 현재 해결되지 않는 부분입니다. (PR 메모를 참조 부탁드리겠습니다.)
	 * */
	@BeforeEach
	void setUp() {
		UserTest.동네_주민 = userRepository.save(UserTest.동네_주민);
		UserTest.신규_주민 = userRepository.save(UserTest.신규_주민);

		CityTest.서울 = cityRepository.save(CityTest.서울);
		CityTest.경기도 = cityRepository.save(CityTest.경기도);
		CityTest.제주도 = cityRepository.save(CityTest.제주도);

		ItemTest.연필 = itemService.create(ItemTest.연필);
		ItemTest.공책 = itemRepository.save(ItemTest.공책);
		ItemTest.커피쿠폰 = itemRepository.save(ItemTest.커피쿠폰);
	}

	@Test
	void 상품_등록() {
		//given, when
		Item saved = itemService.findById(ItemTest.연필.getId());

		//given, when
		assertEquals(saved, ItemTest.연필);
	}

	@Test
	void 아이템_목록_조회_By_유저() {
		//given
		ItemSearchCondition condition = ItemSearchCondition.of(UserTest.동네_주민.getId(), null, null);

		//when
		List<Item> items = itemService.findItemsByCondition(condition);

		//then
		assertEquals(items.size(), 2);
	}

	@Test
	void 아이템_목록_조회_By_도시() {
		//given
		ItemSearchCondition condition = ItemSearchCondition.of(null, CityTest.서울.getId(), null);

		//when
		List<Item> items = itemService.findItemsByCondition(condition);

		//then
		assertEquals(items.size(), 2);
	}

	@Test
	void 아이템_목록_조회_By_키워드() {
		//given
		ItemSearchCondition condition = ItemSearchCondition.of(null, null, "연필");

		//when
		List<Item> items = itemService.findItemsByCondition(condition);

		//then
		assertEquals(items.size(), 1);
	}

	@Test
	void 아이템_목록_조회_By_도시_And_키워드() {
		//given
		ItemSearchCondition condition = ItemSearchCondition.of(null, CityTest.서울.getId(), "연필");

		//when
		List<Item> items = itemService.findItemsByCondition(condition);

		//then
		assertEquals(items.size(), 1);
	}

	@Test
	void 아이템_삭제() {
		//given
		Long id = ItemTest.커피쿠폰.getId();

		//when
		itemService.deleteItemById(id);

		//then
		assertThatThrownBy(() -> itemService.findById(id))
			.isInstanceOf(ItemNotFoundException.class);
	}
}
