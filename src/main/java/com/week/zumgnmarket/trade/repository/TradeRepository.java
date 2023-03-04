package com.week.zumgnmarket.trade.repository;

import com.week.zumgnmarket.trade.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    Trade findByProductId(Long productId);
}
