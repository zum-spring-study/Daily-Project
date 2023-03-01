package com.week.zumgnmarket.service.trade;

import com.week.zumgnmarket.endpoint.trade.dto.buy.TradeRequest;
import com.week.zumgnmarket.entity.Member;
import com.week.zumgnmarket.entity.Product;
import com.week.zumgnmarket.entity.Trade;

public interface PurchaseStrategy {

    Trade purchase(Product product, Member buyer, Member seller, TradeRequest tradeRequest);

}
