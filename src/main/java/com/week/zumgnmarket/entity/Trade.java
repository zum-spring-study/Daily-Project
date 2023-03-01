package com.week.zumgnmarket.entity;

import com.week.zumgnmarket.enums.BuyStatus;
import com.week.zumgnmarket.enums.TradeStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "trade_info")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trader_id")
    private Member trader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    private Member consumer;

    @Column(name = "consumer_suggest_status")
    private BuyStatus consumerSuggestStatus;

    @Column(name = "consumer_suggest_price")
    private Long consumerSuggestPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(name = "trade_activate")
    private TradeStatus status;

    public Trade(Member trader, Member consumer, BuyStatus consumerSuggestStatus, Long consumerSuggestPrice, Product product) {
        this.trader = trader;
        this.consumer = consumer;
        this.consumerSuggestStatus = consumerSuggestStatus;
        this.consumerSuggestPrice = consumerSuggestPrice;
        this.product = product;
    }
}
