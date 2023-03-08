package com.week.zumgnmarket.item.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.week.zumgnmarket.city.entity.City;
import com.week.zumgnmarket.city.service.CityService;
import com.week.zumgnmarket.item.dto.ItemRequest;
import com.week.zumgnmarket.item.dto.ItemSearchCondition;
import com.week.zumgnmarket.item.entity.Item;
import com.week.zumgnmarket.item.entity.ItemRepository;
import com.week.zumgnmarket.item.exception.ItemNotFoundException;
import com.week.zumgnmarket.user.entity.User;
import com.week.zumgnmarket.user.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	private final UserService userService;

	@Autowired
	private final CityService cityService;

	public Item create(Item item) {
		return itemRepository.save(item);
	}

	@Transactional(readOnly = true)
	public Item findById(Long id) {
		return itemRepository.findById(id)
			.orElseThrow(() -> new ItemNotFoundException("Item not found with id: " + id));
	}

	@Transactional(readOnly = true)
	public List<Item> findItemsByCondition(ItemSearchCondition request) {
		return itemRepository.findItemsBySearchOption(request.getUserId(), request.getCityId(), request.getKeyword());
	}

	public void deleteItemById(Long id) {
		itemRepository.deleteById(id);
	}

	public Item toItem(ItemRequest request) {
		User user = userService.findUserById(request.getUserId());
		City city = cityService.findCityById(request.getCityId());
		return Item.builder()
			.name(request.getName())
			.description(request.getDescription())
			.price(request.getPrice())
			.pictureUrl(request.getPictureUrl())
			.owner(user)
			.city(city)
			.build();
	}
}
