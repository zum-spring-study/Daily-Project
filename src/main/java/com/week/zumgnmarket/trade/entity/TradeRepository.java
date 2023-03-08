package com.week.zumgnmarket.trade.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.week.zumgnmarket.user.entity.User;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long> {
	boolean existsByTradeTime(TradeTime time);
}
