package com.week.zumgnmarket.repository;

import com.week.zumgnmarket.entity.Buyer;
import com.week.zumgnmarket.entity.Musical;
import com.week.zumgnmarket.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseJpaRepository extends JpaRepository<Purchase, Integer> {
    boolean existsByBuyerAndMusical(Buyer buyer, Musical musical);
}
