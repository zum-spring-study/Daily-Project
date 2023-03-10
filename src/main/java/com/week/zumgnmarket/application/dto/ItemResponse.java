package com.week.zumgnmarket.application.dto;

import com.week.zumgnmarket.constant.TownType;
import com.week.zumgnmarket.domain.Item;
import com.week.zumgnmarket.domain.Shop;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class ItemResponse {
    private String sellerName;
    private TownType region;
    private Integer itemId;
    private String itemName;
    private LocalDate tradingDate;
    private boolean tradeStatus;

    public static ItemResponse of(Item item) {
        return ItemResponse.builder()
                .sellerName(item.getSeller().getNickName())
                .region(item.getTown().getRegion())
                .itemId(item.getId())
                .itemName(item.getItemName())
                .tradingDate(item.getTradingDate())
                .tradeStatus(item.isTradeStatus())
                .build();
    }

    public static ItemResponse of(Shop shop) {
        return ItemResponse.builder()
                .sellerName(shop.getItem().getSeller().getNickName())
                .region(shop.getItem().getTown().getRegion())
                .itemId(shop.getItem().getId())
                .itemName(shop.getItem().getItemName())
                .tradingDate(shop.getItem().getTradingDate())
                .tradeStatus(shop.getItem().isTradeStatus())
                .build();
    }
}
