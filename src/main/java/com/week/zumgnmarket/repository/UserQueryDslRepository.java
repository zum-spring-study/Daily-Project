package com.week.zumgnmarket.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.week.zumgnmarket.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.week.zumgnmarket.domain.QTown.town;
import static com.week.zumgnmarket.domain.QUser.user;

@Repository
@RequiredArgsConstructor
public class UserQueryDslRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public User findById(Integer userId) {
        return jpaQueryFactory.selectFrom(user)
                .join(user.town, town).fetchJoin()
                .where(user.id.eq(userId))
                .fetchOne();
    }
}
