package com.week.zumgnmarket.repository.town;

import com.week.zumgnmarket.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownRepository extends JpaRepository<Town, Long> {

    Town findByProductId(Long productId);
}
