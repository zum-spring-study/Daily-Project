package com.week.zumgnmarket.trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.week.zumgnmarket.item.dto.ItemResponse;
import com.week.zumgnmarket.trade.dto.TradeRequest;
import com.week.zumgnmarket.trade.dto.TradeResponse;
import com.week.zumgnmarket.trade.exception.CannotReserveException;
import com.week.zumgnmarket.trade.exception.TradeNotFoundException;
import com.week.zumgnmarket.trade.service.TradeService;
import com.week.zumgnmarket.user.exception.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
@RequestMapping("/trades")
public class TradeController {

	@Autowired
	private final TradeService tradeService;

	@PostMapping("/create")
	public ResponseEntity<TradeResponse> create(@RequestBody TradeRequest request) {
		return ResponseEntity.ok(TradeResponse.of(tradeService.create(tradeService.toTrade(request))));
	}

	@GetMapping("/{tradeId}")
	public ResponseEntity<TradeResponse> findById(@PathVariable Long tradeId) {
		return ResponseEntity.ok(TradeResponse.of(tradeService.findTradeById(tradeId)));
	}

	@PostMapping("/cancel/{tradeId}")
	public ResponseEntity<String> cancel(@PathVariable Long tradeId) {
		tradeService.cancel(tradeId);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/complete/{tradeId}")
	public ResponseEntity<String> completeTrade(@PathVariable Long tradeId) {
		tradeService.completeTrade(tradeId);
		return ResponseEntity.noContent().build();
	}

	@ExceptionHandler(TradeNotFoundException.class)
	public ResponseEntity<String> handleNotFoundException(TradeNotFoundException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}

	@ExceptionHandler(CannotReserveException.class)
	public ResponseEntity<String> handleBadRequestException(CannotReserveException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
}
