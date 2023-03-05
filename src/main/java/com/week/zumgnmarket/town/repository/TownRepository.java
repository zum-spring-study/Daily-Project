package com.week.zumgnmarket.town.repository;

import com.week.zumgnmarket.town.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, Long> {

    Town findByProductId(Long productId);
}
