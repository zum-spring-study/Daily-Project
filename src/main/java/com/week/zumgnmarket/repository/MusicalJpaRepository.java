package com.week.zumgnmarket.repository;

import com.week.zumgnmarket.entity.Musical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicalJpaRepository extends JpaRepository<Musical, Integer> {
}
