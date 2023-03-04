package com.week.zumgnmarket.product.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.week.zumgnmarket.product.entity.Product;
import com.week.zumgnmarket.enums.LocationNames;
import com.week.zumgnmarket.enums.TradeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.week.zumgnmarket.common.QTown.*;
import static com.week.zumgnmarket.product.entity.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductDynamicRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public List<Product> getAllProducts(String location, Pageable pageable) {
        return jpaQueryFactory.selectFrom(product)
                .leftJoin(town).on(town.product.id.eq(product.id))
                .where(town.cityName.eq(LocationNames.valueOf(location))
                        .and(product.status.eq(TradeStatus.WAIT)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
