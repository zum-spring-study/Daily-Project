package com.week.zumgnmarket.service.trade.buyer.menual;

import com.week.zumgnmarket.endpoint.trade.dto.buy.TradeRequest;
import com.week.zumgnmarket.entity.Member;
import com.week.zumgnmarket.entity.Product;
import com.week.zumgnmarket.entity.Trade;
import com.week.zumgnmarket.service.trade.buyer.PurchaseStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectTradeService implements PurchaseStrategy {

    @Override
    public Trade purchase(Product product, Member buyer, Member seller, TradeRequest tradeRequest) {

        return new Trade(seller, buyer, tradeRequest.getTradeStatus(), product.getPrice(), product);
    }
}
