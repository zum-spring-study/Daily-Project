package com.week.zumgnmarket.repository;

import com.week.zumgnmarket.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    Item findByItemName(String itemName);
}
