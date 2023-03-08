package com.week.zumgnmarket.item.entity;

import static com.week.zumgnmarket.item.entity.QItem.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class ItemCustomRepositoryImpl extends QuerydslRepositorySupport implements ItemCustomRepository {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

	public ItemCustomRepositoryImpl() {
		super(Item.class);
	}

	@Override
	public List<Item> findItemsBySearchOption(Long userId, Long cityId, String keyword) {
		return jpaQueryFactory.selectFrom(item)
			.where(
				eqUserId(userId),
				eqCityId(cityId),
				isContainsKeyword(keyword)
			)
			.orderBy(item.createdDate.desc())
			.fetch();
	}

	private BooleanExpression isContainsKeyword(final String keyword) {
		if (!StringUtils.hasText(keyword)) {
			return null;
		}
		return item.name.contains(keyword);
	}

	private BooleanExpression eqUserId(final Long userId) {
		if (userId == null) {
			return null;
		}
		return item.owner.id.eq(userId);
	}

	private BooleanExpression eqCityId(final Long cityId) {
		if (cityId == null) {
			return null;
		}
		return item.city.id.eq(cityId);
	}
}
