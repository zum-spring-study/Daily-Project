package com.week.zumgnmarket.city.entity;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CityTest {
	public static final City 서울 = new City("서울");
	public static final City 경기도 = new City("경기도");
	public static final City 인천 = new City("인천");
	public static final City 강릉 = new City("강릉");
	public static final City 부산 = new City("부산");
	public static final City 제주도 = new City("제주도");

	@Autowired
	private static CityRepository cityRepository;

	public static List<City> 도시_저장_목록() {
		return cityRepository.saveAll(Arrays.asList(서울, 경기도, 인천, 강릉, 부산, 제주도));
	}
}
