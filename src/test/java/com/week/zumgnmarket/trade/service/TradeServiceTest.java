package com.week.zumgnmarket.trade.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.week.zumgnmarket.city.entity.CityRepository;
import com.week.zumgnmarket.city.entity.CityTest;
import com.week.zumgnmarket.item.entity.ItemRepository;
import com.week.zumgnmarket.item.entity.ItemTest;
import com.week.zumgnmarket.trade.entity.Trade;
import com.week.zumgnmarket.trade.entity.TradeTest;
import com.week.zumgnmarket.trade.exception.CannotReserveException;
import com.week.zumgnmarket.user.entity.UserRepository;
import com.week.zumgnmarket.user.entity.UserTest;

@SpringBootTest
@Transactional
public class TradeServiceTest {

	@Autowired
	TradeService tradeService;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CityRepository cityRepository;

	/**
	 * 이슈 미해결
	 * */
	@BeforeEach
	void setUp() {
		UserTest.동네_주민 = userRepository.save(UserTest.동네_주민);
		UserTest.신규_주민 = userRepository.save(UserTest.신규_주민);

		CityTest.서울 = cityRepository.save(CityTest.서울);
		CityTest.경기도 = cityRepository.save(CityTest.경기도);
		CityTest.제주도 = cityRepository.save(CityTest.제주도);

		ItemTest.연필 = itemRepository.save(ItemTest.연필);
		ItemTest.공책 = itemRepository.save(ItemTest.공책);
		ItemTest.커피쿠폰 = itemRepository.save(ItemTest.커피쿠폰);
	}

	@Test
	public void 거래_등록() {
		//given
		Trade trade = Trade.of(UserTest.동네_주민, UserTest.신규_주민, ItemTest.연필, "강남역 11번 출구", "111-111",
			TradeTest.TRADE_TIME);

		//when
		Trade saved = tradeService.create(trade);

		//then
		assertEquals(trade, saved);
	}

	@Test
	@DisplayName("이미 거래가 예약된 시간에는 중복 거래 예약이 불가능하다.")
	public void 거래_등록_실패() {
		//given
		거래_등록();
		Trade trade = Trade.of(UserTest.동네_주민, UserTest.신규_주민, ItemTest.공책, "서초역 2번 출구", "111-111",
			TradeTest.TRADE_TIME);

		//when, then
		assertThatThrownBy(() -> tradeService.create(trade))
			.isInstanceOf(CannotReserveException.class);
	}

	@Test
	public void 거래_완료() {
		//given
		Trade trade = tradeService.create(
			Trade.of(UserTest.동네_주민, UserTest.신규_주민, ItemTest.연필, "강남역 11번 출구", "111-111", TradeTest.TRADE_TIME));

		//when
		tradeService.completeTrade(trade.getId());

		//then
		assertTrue(trade.getItem().isSold());
	}
}
