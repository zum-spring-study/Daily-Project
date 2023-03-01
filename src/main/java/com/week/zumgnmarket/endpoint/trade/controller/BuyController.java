package com.week.zumgnmarket.endpoint.trade.controller;

import com.week.zumgnmarket.endpoint.trade.dto.buy.TradeRequest;
import com.week.zumgnmarket.endpoint.trade.dto.buy.TradeResponse;
import com.week.zumgnmarket.endpoint.trade.facade.BuyFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/trade")
public class BuyController {

    private final BuyFacade buyFacade;

    /*
    * 구매요청
    * DIRECT = 즉시 구매 요청, SCHEDULE = 예약 구매 요청, AUCTION = 경매 구매 요청
    * @pathVariable : productId - 상품 아이디
    * @requestBody : TradeRequest - 구매 요청 정보
    * @return : TradeResponse - 구매 요청 결과
    */
    @PostMapping("/buy/{productId}")
    @Tag(name = "buy", description = "구매 요청 API")
    public TradeResponse buyDirect(@PathVariable("productId") Long productId, @RequestBody TradeRequest tradeRequest) {
        return buyFacade.buy(productId, tradeRequest);
    }

}
