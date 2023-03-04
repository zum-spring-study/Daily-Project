package com.week.zumgnmarket.trade.service.seller;

import com.week.zumgnmarket.trade.dto.sell.TradeSellRequest;
import com.week.zumgnmarket.product.entity.Product;
import com.week.zumgnmarket.trade.entity.Trade;
import com.week.zumgnmarket.enums.TradeStatus;
import com.week.zumgnmarket.product.repository.ProductRepository;
import com.week.zumgnmarket.trade.repository.TradeRepository;
import com.week.zumgnmarket.trade.service.seller.menual.FalseSellService;
import com.week.zumgnmarket.trade.service.seller.menual.RequestSellService;
import com.week.zumgnmarket.trade.service.seller.menual.TrueSellService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SellProcessor {

    private final ProductRepository productRepository;

    private final TradeRepository tradeRepository;

    private final TrueSellService trueSellService;

    private final FalseSellService falseSellService;

    private final RequestSellService requestSellService;

    @Transactional
    public void process(Long productId, TradeSellRequest tradeSellRequest) {

        Product product = findProduct(productId);
        Trade trade = findTrade(productId);

        if (tradeSellRequest.isTureStatus()) {
            trueSellService.sell(product, trade);

        } else if (tradeSellRequest.isFalseStatus()) {

            falseSellService.sell(product, trade);

        } else if (tradeSellRequest.isRequestStatus()) {

            requestSellService.sell(product, trade);

        } else {

            throw new IllegalArgumentException("잘못된 정보입니다.");
        }
    }

    private Product findProduct(Long productId) {
        return productRepository.findByIdAndStatus(productId, TradeStatus.SUGGEST)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 정보입니다."));
    }

    private Trade findTrade(Long productId) {
        return tradeRepository.findByProductId(productId);
    }


}
