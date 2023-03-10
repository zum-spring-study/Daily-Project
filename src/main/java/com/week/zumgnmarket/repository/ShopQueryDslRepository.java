package com.week.zumgnmarket.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.week.zumgnmarket.domain.Shop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.week.zumgnmarket.domain.QItem.item;
import static com.week.zumgnmarket.domain.QShop.shop;
import static com.week.zumgnmarket.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class ShopQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;

    /**
     * 사용자가 구매한 중고 거래 물품 리스트를 조회한다.
     * @param buyerId 사용자 id
     * @return
     */
    public List<Shop> findAllByBuyerId(Integer buyerId) {
        return jpaQueryFactory.selectFrom(shop)
                .join(shop.item, item).fetchJoin()
                .join(shop.buyer, user).fetchJoin()
                .where(user.id.eq(buyerId))
                .fetch();
    }
}
