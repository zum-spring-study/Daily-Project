package com.week.zumgnmarket.trade.controller;

import com.week.zumgnmarket.trade.dto.sell.TradeSellRequest;
import com.week.zumgnmarket.trade.dto.sell.TradeSellResponse;
import com.week.zumgnmarket.trade.facade.SellFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/trade")
@RequiredArgsConstructor
public class SellController {

    private final SellFacade sellFacade;

    /*
    * 판매요청
    * TRUE - 판매 승인 , FALSE - 판매 거절, REQUEST - 판매 금액 재요청
    * @pathVariable : productId - 상품 아이디
    * @requestBody : TradeSellRequest - 판매 요청 정보
     */
    @PostMapping("/sell/{productId}")
    public TradeSellResponse sell(@PathVariable("productId") Long productId, @RequestBody TradeSellRequest tradeSellRequest) {
        return sellFacade.sell(productId, tradeSellRequest);
    }

}
