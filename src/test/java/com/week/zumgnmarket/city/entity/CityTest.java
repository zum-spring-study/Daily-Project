package com.week.zumgnmarket.city.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CityTest {
	public static City 서울 = new City("서울");
	public static City 경기도 = new City("경기도");
	public static City 인천 = new City("인천");
	public static City 강릉 = new City("강릉");
	public static City 부산 = new City("부산");
	public static City 제주도 = new City("제주도");

	@Autowired
	private static CityRepository cityRepository;

	public static void 도시_목록_저장() {
		서울 = cityRepository.save(서울);
		경기도 = cityRepository.save(경기도);
		인천 = cityRepository.save(인천);
		강릉 = cityRepository.save(강릉);
		제주도 = cityRepository.save(제주도);
	}
}
