package com.week.zumgnmarket.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.week.zumgnmarket.constant.TownType;
import com.week.zumgnmarket.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.week.zumgnmarket.domain.QItem.item;
import static com.week.zumgnmarket.domain.QTown.town;
import static com.week.zumgnmarket.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class ItemQueryDslRepository {

    private final JPAQueryFactory jpaQueryFactory;


    /**
     * 지역에 해당하는 중고 거래 물품 리스트를 조회한다.
     * @param region 지역명
     * @return
     */
    public List<Item> findAllByRegion(TownType region) {
        return jpaQueryFactory.selectFrom(item)
                .join(item.seller, user).fetchJoin()
                .join(item.town, town).fetchJoin()
                .where(town.region.eq(region))
                .fetch();
    }

    public Item findById(Integer id) {
        return jpaQueryFactory.selectFrom(item)
                .join(item.seller, user).fetchJoin()
                .join(item.town, town).fetchJoin()
                .where(item.id.eq(id))
                .fetchOne();
    }
}
