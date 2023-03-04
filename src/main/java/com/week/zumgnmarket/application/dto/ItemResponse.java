package com.week.zumgnmarket.application.dto;

import com.week.zumgnmarket.domain.Item;
import com.week.zumgnmarket.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
public class ItemResponse {
    private int sellerId;
    private String region;
    private String itemName;
    private LocalDate tradingDate;
    private boolean tradeStatus;

    public static ItemResponse of(ItemRequest item, User user) {
        return ItemResponse.builder()
                .sellerId(item.getSellerId())
                .region(user.getTown().getRegion())
                .itemName(item.getItemName())
                .tradingDate(item.getTradingDate())
                .tradeStatus(item.isTradeStatus())
                .build();
    }
}
