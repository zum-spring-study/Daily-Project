package com.week.zumgnmarket.trade.service.buyer;

import com.week.zumgnmarket.trade.dto.buy.TradeRequest;
import com.week.zumgnmarket.member.entity.Member;
import com.week.zumgnmarket.product.entity.Product;
import com.week.zumgnmarket.trade.entity.Trade;

public interface PurchaseStrategy {

    Trade purchase(Product product, Member buyer, Member seller, TradeRequest tradeRequest);

}
