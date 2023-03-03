package com.week.zumgnmarket.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ItemRequest {

    private String itemName;
    private int sellerId;
    private LocalDate tradingDate;
    private boolean tradeStatus;
}
