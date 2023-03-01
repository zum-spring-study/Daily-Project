package com.week.zumgnmarket.service.trade;

import com.week.zumgnmarket.endpoint.trade.dto.buy.TradeRequest;
import com.week.zumgnmarket.entity.Member;
import com.week.zumgnmarket.entity.Product;
import com.week.zumgnmarket.entity.Trade;
import org.springframework.stereotype.Service;

@Service
public class AuctionTradeService implements PurchaseStrategy {

    @Override
    public Trade purchase(Product product, Member buyer, Member seller, TradeRequest tradeRequest) {

        return new Trade(seller, buyer, tradeRequest.getTradeStatus(), tradeRequest.getPrice(), product);
    }
}
