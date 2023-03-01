package com.week.zumgnmarket.repository.trade;

import com.week.zumgnmarket.entity.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    Trade findByProductId(Long productId);
}
