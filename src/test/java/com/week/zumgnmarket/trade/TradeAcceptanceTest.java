package com.week.zumgnmarket.trade;

import com.week.zumgnmarket.enums.BuyStatus;
import com.week.zumgnmarket.enums.LocationNames;
import com.week.zumgnmarket.enums.SellStatus;
import com.week.zumgnmarket.enums.TradeStatus;
import com.week.zumgnmarket.member.dto.MemberRequest;
import com.week.zumgnmarket.member.dto.MemberResponse;
import com.week.zumgnmarket.member.facade.MemberFacade;
import com.week.zumgnmarket.product.dto.ProductRequest;
import com.week.zumgnmarket.product.dto.ProductResponse;
import com.week.zumgnmarket.product.facade.ProductFacade;
import com.week.zumgnmarket.trade.dto.buy.TradeRequest;
import com.week.zumgnmarket.trade.dto.buy.TradeResponse;
import com.week.zumgnmarket.trade.dto.sell.TradeSellRequest;
import com.week.zumgnmarket.trade.dto.sell.TradeSellResponse;
import com.week.zumgnmarket.trade.facade.BuyFacade;
import com.week.zumgnmarket.trade.facade.SellFacade;
import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DisplayName("상품 거래 시나리오 테스트")
@Transactional
public class TradeAcceptanceTest {

  @Autowired
  ProductFacade productFacade;

  @Autowired
  MemberFacade memberFacade;


  @Autowired
  BuyFacade buyFacade;

  @Autowired
  SellFacade sellFacade;

  ProductResponse response_1 = new ProductResponse();

  ProductResponse response_2 = new ProductResponse();
  ProductResponse response_3 = new ProductResponse();

  MemberRequest memberRequest_1 = new MemberRequest();

  MemberRequest memberRequest_2 = new MemberRequest();
  MemberRequest memberRequest_3 = new MemberRequest();


  @BeforeEach
  void setUp() {
    memberRequest_1 = new MemberRequest("테스트유저1", LocationNames.SEOUL);
    memberRequest_2 = new MemberRequest("테스트유저2", LocationNames.SEOUL);
    memberRequest_3 = new MemberRequest("테스트유저3", LocationNames.SEOUL);

    MemberResponse signup_1 = memberFacade.signup(memberRequest_1);
    MemberResponse signup_2 = memberFacade.signup(memberRequest_2);
    MemberResponse signup_3 = memberFacade.signup(memberRequest_3);

    ProductRequest productRequest_1 = new ProductRequest(signup_1.getMember().getName(), "테스트상품1", "테스트를 위한 상품입니다.", LocationNames.SEOUL, 10000L, TradeStatus.SELL);
    ProductRequest productRequest_2 = new ProductRequest(signup_2.getMember().getName(), "테스트상품2", "테스트를 위한 상품입니다.", LocationNames.SEOUL, 10000L, TradeStatus.SELL);
    ProductRequest productRequest_3 = new ProductRequest(signup_3.getMember().getName(), "테스트상품3", "테스트를 위한 상품입니다.", LocationNames.SEOUL, 10000L, TradeStatus.SELL);

    response_1 = productFacade.addProduct(productRequest_1);
    response_2 = productFacade.addProduct(productRequest_2);
    response_3 = productFacade.addProduct(productRequest_3);
  }

  @Test
  @DisplayName("상품 즉시 구매 테스트")
  void direct_buy() {
    // DIRECT BUY Request
    TradeRequest tradeRequest = new TradeRequest(memberRequest_1.getName(), BuyStatus.DIRECT, response_2.getPrice(), "즉시구매를 요청합니다.", LocalDate.now());
    TradeResponse buyResponse = buyFacade.buy(response_2.getId(), tradeRequest);

//    // DIRECT_SELL Request ( 승인시 )
//    TradeSellRequest trueSellRequest = new TradeSellRequest(SellStatus.TRUE, "강남역 9시 출구에서 거래해요!");
//    TradeSellResponse trueResponse = sellFacade.sell(buyResponse.getProductId(), trueSellRequest);
//
//    Assertions.assertEquals(trueResponse.getTradeStatus(), SellStatus.TRUE);


    // DIRECT_SELL Request ( 거절시 )
    TradeSellRequest falseFailRequest = new TradeSellRequest(SellStatus.FALSE, "강남역 9시 출구에서 거래해요!");
    TradeSellResponse falseResponse = sellFacade.sell(buyResponse.getProductId(), falseFailRequest);

    Assertions.assertEquals(falseResponse.getTradeStatus(), SellStatus.FALSE);
  }

  @Test
  @DisplayName("상품 예약 구매 테스트")
  void schedule_buy() {
    // SCHEDULE_BUY Request
    TradeRequest tradeRequest = new TradeRequest(memberRequest_2.getName(), BuyStatus.SCHEDULE, response_3.getPrice(), "에약구매를 요청합니다.", LocalDate.now().plusDays(1));
    TradeResponse buyResponse = buyFacade.buy(response_3.getId(), tradeRequest);


    // SCHEDULE_SELL Request ( 승인시 )
    TradeSellRequest trueSellRequest = new TradeSellRequest(SellStatus.TRUE, "강남역 9시 출구에서 거래해요!");
    TradeSellResponse trueResponse = sellFacade.sell(buyResponse.getProductId(), trueSellRequest);

    Assertions.assertEquals(trueResponse.getTradeStatus(), SellStatus.TRUE);

    // SCHEDULE_SELL Request ( 거절시 )
    TradeSellRequest falseTradeRequest = new TradeSellRequest(SellStatus.FALSE, "강남역 9시 출구에서 거래해요!");
    TradeSellResponse falseResponse = sellFacade.sell(buyResponse.getProductId(), falseTradeRequest);

    Assertions.assertEquals(falseResponse.getTradeStatus(), SellStatus.FALSE);

  }

  @Test
  @DisplayName("경매 구매 테스트")
  void auction_buy() {
    // AUCTION_BUY Request
    TradeRequest tradeRequest = new TradeRequest(memberRequest_1.getName(), BuyStatus.AUCTION, response_3.getPrice() + 1000, "경매구매를 요청합니다.", LocalDate.now().plusDays(2));
    TradeResponse buyResponse = buyFacade.buy(response_3.getId(), tradeRequest);

    // AUCTION_SELL Request
    TradeSellRequest tradeSellRequest = new TradeSellRequest(SellStatus.REQUEST, "판매금액이 마음에 안들어요... 더 제시부탁드려요!");
    TradeSellResponse sell = sellFacade.sell(buyResponse.getProductId(), tradeSellRequest);

    Assertions.assertEquals(sell.getTradeStatus(), SellStatus.REQUEST);

  }

}
