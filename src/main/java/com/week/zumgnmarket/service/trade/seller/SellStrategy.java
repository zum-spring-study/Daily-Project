package com.week.zumgnmarket.service.trade.seller;

import com.week.zumgnmarket.entity.Product;
import com.week.zumgnmarket.entity.Trade;

public interface SellStrategy {

    void sell(Product product, Trade trade);
}
