package com.week.zumgnmarket.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.week.zumgnmarket.order.dto.OrderRequest;
import com.week.zumgnmarket.order.dto.OrderResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

	private final OrderFacade orderFacade;

	@PostMapping("/tickets")
	public ResponseEntity<OrderResponse> orderTickets(@RequestBody OrderRequest request) {
		return ResponseEntity.ok(orderFacade.order(request));
	}

}
