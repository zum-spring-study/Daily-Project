package com.week.zumgnmarket.fecade.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class TicketRequest {
    private Integer buyerId;
    private Integer musicalId;
    private LocalDate TicketingDate;

    public static TicketRequest of(Integer buyerId, Integer musicalId, LocalDate TicketingDate) {
        return TicketRequest.builder()
                .buyerId(buyerId)
                .musicalId(musicalId)
                .TicketingDate(TicketingDate).build();
    }
}
