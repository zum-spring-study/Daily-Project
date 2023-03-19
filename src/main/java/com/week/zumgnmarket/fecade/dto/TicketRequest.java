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
}
