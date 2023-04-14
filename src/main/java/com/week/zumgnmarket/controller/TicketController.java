package com.week.zumgnmarket.controller;

import com.week.zumgnmarket.fecade.TicketFacade;
import com.week.zumgnmarket.fecade.dto.TicketRequest;
import com.week.zumgnmarket.fecade.dto.TicketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TicketResponse> buy(@RequestBody TicketRequest ticketRequest) {
        return ResponseEntity.ok(ticketFacade.buy(ticketRequest));
    }
}
