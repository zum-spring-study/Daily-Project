package com.week.zumgnmarket.fecade.dto;

import com.week.zumgnmarket.entity.Purchase;
import lombok.*;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketResponse {

    private Integer buyerId;
    private String title;
    private boolean isPurchase;

    public static TicketResponse of(Purchase purchase) {
        return TicketResponse.builder()
                .buyerId(purchase.getBuyerId())
                .title(purchase.getTitle())
                .isPurchase(true).build();
    }
}
