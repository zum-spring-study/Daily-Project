package com.week.zumgnmarket.endpoint.trade.dto.sell;

import com.week.zumgnmarket.enums.SellStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeSellRequest {

    private Long productId;
    private SellStatus tradeStatus;
    private String description;
}
