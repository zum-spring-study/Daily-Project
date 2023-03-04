package com.week.zumgnmarket.repository;

import com.week.zumgnmarket.domain.Item;
import com.week.zumgnmarket.domain.Town;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findAllByTown(Town town);
}
