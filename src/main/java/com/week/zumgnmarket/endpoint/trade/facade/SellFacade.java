package com.week.zumgnmarket.endpoint.trade.facade;

import com.week.zumgnmarket.endpoint.trade.dto.sell.TradeSellRequest;
import com.week.zumgnmarket.endpoint.trade.dto.sell.TradeSellResponse;
import com.week.zumgnmarket.service.trade.seller.SellProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SellFacade {

    private final SellProcessor sellProcessor;
    public TradeSellResponse sell(Long productId, TradeSellRequest tradeSellRequest) {

        try{
            sellProcessor.process(productId, tradeSellRequest);
            return TradeSellResponse.of(productId, tradeSellRequest.getTradeStatus(), tradeSellRequest.getDescription());

        }catch (Exception e){
            return TradeSellResponse.of(productId, tradeSellRequest.getTradeStatus(), e.getMessage());
        }
    }
}
