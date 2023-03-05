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


/*
  * 거래 예외 테스트
  * 거래 예외 테스트는 거래시 실패요소로 구성된 테스트입니다.
  * -> 같은 날짜에 구매를 희망할 경우 예외처리
 */
@SpringBootTest
@DisplayName("거래 시나리오 예외 테스트")
@Transactional
public class TradeExceptionTest {

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

  @BeforeEach
  void setUp() {
    memberRequest_1 = new MemberRequest("테스트유저1", LocationNames.SEOUL);
    memberRequest_2 = new MemberRequest("테스트유저2", LocationNames.SEOUL);

    MemberResponse signup_1 = memberFacade.signup(memberRequest_1);
    MemberResponse signup_2 = memberFacade.signup(memberRequest_2);

    ProductRequest productRequest_1 = new ProductRequest(signup_1.getMember().getName(), "테스트상품1", "테스트를 위한 상품입니다.", LocationNames.SEOUL, 10000L, TradeStatus.SELL);
    ProductRequest productRequest_2 = new ProductRequest(signup_2.getMember().getName(), "테스트상품2", "테스트를 위한 상품입니다.", LocationNames.SEOUL, 10000L, TradeStatus.SELL);
    ProductRequest productRequest_3 = new ProductRequest(signup_2.getMember().getName(), "테스트상품3", "테스트를 위한 상품입니다.", LocationNames.SEOUL, 10000L, TradeStatus.SELL);

    response_1 = productFacade.addProduct(productRequest_1);
    response_2 = productFacade.addProduct(productRequest_2);
    response_3 = productFacade.addProduct(productRequest_3);

    // 현재 즉시거래 요청을 통해 거래가 진행중임을 예시로 가져감
    // 현재 거래 member1 은 거래 대기중인상황
    TradeRequest tradeRequest = new TradeRequest(memberRequest_1.getName(), BuyStatus.DIRECT, response_2.getPrice(), "즉시구매를 요청합니다.", LocalDate.now());
    TradeResponse buyResponse = buyFacade.buy(response_2.getId(), tradeRequest);

    TradeSellRequest trueSellRequest = new TradeSellRequest(SellStatus.TRUE, "강남역 9시 출구에서 거래해요!");
    sellFacadeRequest(buyResponse, trueSellRequest, SellStatus.TRUE);
  }


  @Test
  @DisplayName("같은 날짜 거래 요청할 경우")
  void sameDateTradeRequest() {
    TradeRequest tradeRequest = new TradeRequest(memberRequest_1.getName(), BuyStatus.DIRECT, response_3.getPrice(), "즉시구매를 요청합니다.", LocalDate.now());
    TradeResponse buyResponse = buyFacade.buy(response_3.getId(), tradeRequest);

    Assertions.assertEquals(buyResponse.getIsSuccess(), false);
  }

  private void sellFacadeRequest(TradeResponse buyResponse, TradeSellRequest tradeSellRequest, SellStatus sellStatus) {
    TradeSellResponse sell = sellFacade.sell(buyResponse.getProductId(), tradeSellRequest);

    Assertions.assertEquals(sell.getTradeStatus(), sellStatus);
  }
}
