package com.week.zumgnmarket.item.entity;

import java.util.List;

public interface ItemCustomRepository {
	List<Item> findItemsBySearchOption(Long userId, Long cityId, String keyword);
}
