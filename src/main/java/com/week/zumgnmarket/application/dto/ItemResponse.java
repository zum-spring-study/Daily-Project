package com.week.zumgnmarket.application.dto;

import com.week.zumgnmarket.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Getter
public class ItemResponse {
    private int itemId;
    private String itemName;
    private UserResponse user;
    private LocalDate tradingDate;
    private boolean tradeStatus;

    public static ItemResponse of(Item item) {
        return ItemResponse.builder()
                .itemId(item.getId())
                .itemName(item.getItemName())
                .user(UserResponse.of(item.getSeller()))
                .tradingDate(item.getTradingDate())
                .tradeStatus(item.isTradeStatus())
                .build();
    }
}
