package com.week.zumgnmarket.trade.service.buyer.menual;

import com.week.zumgnmarket.trade.dto.buy.TradeRequest;
import com.week.zumgnmarket.member.entity.Member;
import com.week.zumgnmarket.product.entity.Product;
import com.week.zumgnmarket.trade.entity.Trade;
import com.week.zumgnmarket.trade.service.buyer.PurchaseStrategy;
import org.springframework.stereotype.Service;

@Service
public class AuctionTradeService implements PurchaseStrategy {

    @Override
    public Trade purchase(Product product, Member buyer, Member seller, TradeRequest tradeRequest) {

        return new Trade(seller, buyer, tradeRequest.getTradeStatus(), tradeRequest.getPrice(), product);
    }
}
