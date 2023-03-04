package com.week.zumgnmarket.repository;

import com.week.zumgnmarket.domain.Town;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TownJpaRepository extends JpaRepository<Town, Integer> {
    Town findByRegion(String region);
}
