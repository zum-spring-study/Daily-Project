package com.week.zumgnmarket.repository.trade.dynamic;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.week.zumgnmarket.entity.QTrade;
import com.week.zumgnmarket.entity.QTradeSchedule;
import com.week.zumgnmarket.entity.Trade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.week.zumgnmarket.entity.QTrade.trade;
import static com.week.zumgnmarket.entity.QTradeSchedule.tradeSchedule;

@Repository
@RequiredArgsConstructor
public class TradeDynamicRepository {

    private final JPAQueryFactory queryFactory;

    public Boolean isPossibleTradeMember(Long consumerId, LocalDate scheduleDate) {

        // 동일한 consumerId로 등록된 trade 조회
        BooleanExpression tradeConsumerIdEq = trade.consumer.id.eq(consumerId);
        List<Trade> trades = queryFactory.selectFrom(trade)
                .where(tradeConsumerIdEq)
                .fetch();

        // trade가 존재하지 않으면 상품 구매가 가능
        if (trades.isEmpty()) {
            return true;
        }

        // trade가 존재하면 등록된 trade_schedule 조회
        List<LocalDate> tradeDates = queryFactory.select(tradeSchedule.time)
                .from(tradeSchedule)
                .where(tradeSchedule.trade.in(trades))
                .fetch();

        // 파라미터로 받은 scheduleDate가 이미 trade_schedule에 등록되어 있으면 상품 구매가 불가능
        return !tradeDates.contains(scheduleDate);
    }

}
