package com.week.zumgnmarket.city.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.week.zumgnmarket.city.entity.City;
import com.week.zumgnmarket.city.entity.CityRepository;
import com.week.zumgnmarket.city.exception.CityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	@Transactional(readOnly = true)
	public City findCityById(Long id) {
		return cityRepository.findById(id)
			.orElseThrow(() -> new CityNotFoundException("City not found with id: " + id));
	}
}
