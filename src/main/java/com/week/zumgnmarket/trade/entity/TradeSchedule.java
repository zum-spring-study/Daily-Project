package com.week.zumgnmarket.trade.entity;

import com.week.zumgnmarket.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "trade_scudule")
public class TradeSchedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trade_id")
    private Trade trade;

    @Column(name ="trade_time")
    private LocalDate time;

    public TradeSchedule(Trade trade, LocalDate time) {
        this.trade = trade;
        this.time = time;
    }
}
