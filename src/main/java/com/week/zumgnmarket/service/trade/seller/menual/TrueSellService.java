package com.week.zumgnmarket.service.trade.seller.menual;

import com.week.zumgnmarket.entity.Product;
import com.week.zumgnmarket.entity.Trade;
import com.week.zumgnmarket.enums.TradeStatus;
import com.week.zumgnmarket.repository.product.ProductRepository;
import com.week.zumgnmarket.repository.trade.TradeRepository;
import com.week.zumgnmarket.service.trade.seller.SellStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrueSellService implements SellStrategy {

    private final ProductRepository productRepository;

    private final TradeRepository tradeRepository;

    @Override
    public void sell(Product product, Trade trade) {
        product.updateStatus(TradeStatus.COMPLETE);

        trade.updateStatus(TradeStatus.COMPLETE);

        tradeRepository.save(trade);
        productRepository.save(product);
    }
}
