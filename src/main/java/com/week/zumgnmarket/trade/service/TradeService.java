package com.week.zumgnmarket.trade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.week.zumgnmarket.item.entity.Item;
import com.week.zumgnmarket.item.service.ItemService;
import com.week.zumgnmarket.trade.dto.TradeRequest;
import com.week.zumgnmarket.trade.entity.Trade;
import com.week.zumgnmarket.trade.entity.TradeRepository;
import com.week.zumgnmarket.trade.entity.TradeTime;
import com.week.zumgnmarket.trade.exception.CannotReserveException;
import com.week.zumgnmarket.trade.exception.TradeErrorMessage;
import com.week.zumgnmarket.trade.exception.TradeNotFoundException;
import com.week.zumgnmarket.user.entity.User;
import com.week.zumgnmarket.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TradeService {

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private ItemService itemService;

	public Trade create(Trade trade){
		checkTradeTime(trade.getTradeTime());
		return tradeRepository.save(trade);
	}

	@Transactional(readOnly = true)
	public Trade findTradeById(Long id) {
		return tradeRepository.findById(id)
			.orElseThrow(() -> new TradeNotFoundException(TradeErrorMessage.TRADE_NOT_FOUND_BY_ID + id));
	}

	@Transactional(readOnly = true)
	public void checkTradeTime(TradeTime time) throws CannotReserveException {
		if (tradeRepository.existsByTradeTime(time)) {
			throw new CannotReserveException(TradeErrorMessage.TRADE_TIME_DUPLICATED);
		}
	}

	public void cancel(Long id) {
		findTradeById(id).cancel();
	}

	public void completeTrade(Long id) {
		findTradeById(id).complete();
	}

	public Trade toTrade(TradeRequest request) {
		Item item = itemService.findById(request.getItemId());
		User seller = item.getOwner();
		User buyer = userService.findUserById(request.getBuyerId());
		return Trade.of(seller, buyer, item, request.getAddress(), request.getZipCode(), request.getDateTime());
	}
}
