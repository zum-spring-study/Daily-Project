package com.week.zumgnmarket.domain;

import com.week.zumgnmarket.application.dto.ItemRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
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

    @Column(name = "item_name")
    private String itemName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "town_idx")
    private Town town;

    @Column(name = "trading_date")
    private LocalDate tradingDate;

    @Column(name = "trade_status")
    private boolean tradeStatus;

    public static Item of(ItemRequest itemDto, User user) {
        return Item.builder()
                .seller(user)
                .itemName(itemDto.getItemName())
                .town(user.getTown())
                .tradingDate(itemDto.getTradingDate())
                .tradeStatus(itemDto.isTradeStatus())
                .build();
    }
}
