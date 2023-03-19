package com.week.zumgnmarket.controller;

import com.week.zumgnmarket.fecade.TicketFacade;
import com.week.zumgnmarket.fecade.dto.TicketRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketFacade ticketFacade;

    @GetMapping("/buy")
    public void buy(@RequestBody TicketRequest ticketRequest) {
        ticketFacade.buyTicket(ticketRequest);
    }
}
