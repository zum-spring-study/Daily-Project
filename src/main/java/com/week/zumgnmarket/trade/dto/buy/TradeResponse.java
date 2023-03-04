package com.week.zumgnmarket.trade.dto.buy;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeResponse {

    private Long productId;
    private Boolean isSuccess;
    private String message;

    public static TradeResponse of(Long productId, Boolean isSuccess, String message) {
        return TradeResponse.builder()
                .productId(productId)
                .isSuccess(isSuccess)
                .message(message)
                .build();
    }
}
