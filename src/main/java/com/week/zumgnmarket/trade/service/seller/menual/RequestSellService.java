package com.week.zumgnmarket.trade.service.seller.menual;

import com.week.zumgnmarket.product.entity.Product;
import com.week.zumgnmarket.trade.entity.Trade;
import com.week.zumgnmarket.enums.TradeStatus;
import com.week.zumgnmarket.product.repository.ProductRepository;
import com.week.zumgnmarket.trade.repository.TradeRepository;
import com.week.zumgnmarket.trade.service.seller.SellStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestSellService implements SellStrategy {

    private final ProductRepository productRepository;

    private final TradeRepository tradeRepository;


    @Override
    public void sell(Product product, Trade trade) {
        product.updateStatus(TradeStatus.RESUGGEST);

        trade.updateStatus(TradeStatus.RESUGGEST);

        tradeRepository.save(trade);
        productRepository.save(product);

    }
}
