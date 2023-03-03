package com.week.zumgnmarket.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_idx")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private User seller;

    @Column(name = "item")
    private String item;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "town_idx")
    private Town town;

    @Column(name = "trading_date")
    private LocalDate tradingDate;

    @Column(name = "trade_status")
    private boolean tradeStatus;
}
