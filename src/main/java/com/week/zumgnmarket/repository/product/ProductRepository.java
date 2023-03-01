package com.week.zumgnmarket.repository.product;

import com.week.zumgnmarket.entity.Product;
import com.week.zumgnmarket.enums.TradeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndStatus(Long id, TradeStatus status);

}
