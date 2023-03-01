package com.week.zumgnmarket.repository.product;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.week.zumgnmarket.entity.Product;
import com.week.zumgnmarket.enums.LocationNames;
import com.week.zumgnmarket.enums.TradeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.week.zumgnmarket.entity.QProduct.*;
import static com.week.zumgnmarket.entity.QTown.*;

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
