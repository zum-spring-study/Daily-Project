package com.week.zumgnmarket.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
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
     * 자신의 동네에 올라온 중고 거래 물품 리스트를 조회한다
     * @param townId 동네 id
     * @return
     */
    public List<Item> findAllByTown(Integer townId) {
        return jpaQueryFactory.selectFrom(item)
                .join(item.town, town).fetchJoin()
                .where(town.id.eq(townId))
                .fetch();
    }

    /**
     * 자신이 판매한 중고 거래 물품 리스트를 조회한다
     * @param userId 판매자 id
     * @return
     */
    public List<Item> findAllByUser(Integer userId) {
        return jpaQueryFactory.selectFrom(item)
                .join(item.seller, user).fetchJoin()
                .where(user.id.eq(userId))
                .fetch();
    }
}
