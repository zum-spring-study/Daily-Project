package com.week.zumgnmarket.trade.dto.buy;

import com.week.zumgnmarket.enums.BuyStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeRequest {

    private String memberName;
    private BuyStatus tradeStatus;
    private Long price;
    private String decription;
    private LocalDate scheduleDate;

    public Boolean isDirectStatus() {
        return tradeStatus == BuyStatus.DIRECT;
    }

    public Boolean isScheduleStatus() {
        return tradeStatus == BuyStatus.SCHEDULE;
    }

    public Boolean isAuctionStatus() {
        return tradeStatus == BuyStatus.AUCTION;
    }
}
