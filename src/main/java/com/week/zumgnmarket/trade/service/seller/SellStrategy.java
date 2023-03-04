package com.week.zumgnmarket.trade.service.seller;

import com.week.zumgnmarket.product.entity.Product;
import com.week.zumgnmarket.trade.entity.Trade;

public interface SellStrategy {

    void sell(Product product, Trade trade);
}
