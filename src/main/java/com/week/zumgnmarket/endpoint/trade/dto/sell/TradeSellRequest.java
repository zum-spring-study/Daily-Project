package com.week.zumgnmarket.endpoint.trade.dto.sell;

import com.week.zumgnmarket.enums.SellStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeSellRequest {
    private SellStatus tradeStatus;

    private String description;

    public Boolean isTureStatus() {
        return tradeStatus == SellStatus.TRUE;
    }

    public Boolean isFalseStatus() {
        return tradeStatus == SellStatus.FALSE;
    }


    public Boolean isRequestStatus() {
        return tradeStatus == SellStatus.REQUEST;
    }

}
