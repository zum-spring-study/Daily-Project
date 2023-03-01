package com.week.zumgnmarket.endpoint.trade.dto.sell;

import com.week.zumgnmarket.enums.SellStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeSellResponse {

    private Long productId;
    private SellStatus tradeStatus;
    private String description;

    public static TradeSellResponse of(Long productId, SellStatus tradeStatus, String description) {
        return TradeSellResponse.builder()
                .productId(productId)
                .tradeStatus(tradeStatus)
                .description(description)
                .build();
    }

}
