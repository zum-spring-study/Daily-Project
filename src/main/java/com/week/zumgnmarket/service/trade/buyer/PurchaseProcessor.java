package com.week.zumgnmarket.service.trade.buyer;

import com.week.zumgnmarket.endpoint.trade.dto.buy.TradeRequest;
import com.week.zumgnmarket.entity.Member;
import com.week.zumgnmarket.entity.Product;
import com.week.zumgnmarket.entity.Trade;
import com.week.zumgnmarket.entity.TradeSchedule;
import com.week.zumgnmarket.enums.TradeStatus;
import com.week.zumgnmarket.repository.member.MemberRepository;
import com.week.zumgnmarket.repository.product.ProductRepository;
import com.week.zumgnmarket.repository.trade.TradeRepository;
import com.week.zumgnmarket.repository.trade.TradeScheduleRepository;
import com.week.zumgnmarket.repository.trade.dynamic.TradeDynamicRepository;
import com.week.zumgnmarket.service.trade.buyer.menual.AuctionTradeService;
import com.week.zumgnmarket.service.trade.buyer.menual.DirectTradeService;
import com.week.zumgnmarket.service.trade.buyer.menual.ScheduleTradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Component
@RequiredArgsConstructor
public class PurchaseProcessor {

    private final DirectTradeService directTradeService;

    private final ScheduleTradeService scheduleTradeService;

    private final AuctionTradeService auctionTradeService;

    private final MemberRepository memberRepository;

    private final ProductRepository productRepository;

    private final TradeRepository tradeRepository;

    private final TradeScheduleRepository tradeScheduleRepository;

    private final TradeDynamicRepository tradeDynamicRepository;

    @Transactional
    public void process(Long productId, TradeRequest tradeRequest) {

        Product product = findProduct(productId);
        Member seller = findMember(tradeRequest.getMemberName());
        Member buyer = findMember(product.getMember().getName());

        if (tradeDynamicRepository.isPossibleTradeMember(buyer.getId(), tradeRequest.getScheduleDate())) {

            tradeProcess(product, seller, buyer, tradeRequest);

        } else {
            throw new IllegalArgumentException("이미 진행중인 거래가 있는 구매자입니다.");
        }
    }


    private Product findProduct(Long productId) {
        return productRepository.findByIdAndStatus(productId, TradeStatus.WAIT)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않거나 거래중입니다."));
    }

    private Member findMember(String memberName) {
        return memberRepository.findByName(memberName)
                .orElseThrow(() -> new IllegalArgumentException("구매자가 존재하지 않습니다."));
    }

    private void ischeckSelfProduct(Product product, Member member) {
        if(product.getMember().equals(member)) {
            throw new IllegalArgumentException("자신의 상품은 구매할 수 없습니다.");
        }
    }

    private void saveTradeSchedule (Trade trade, LocalDate tradeDate) {
        trade.updateStatus(TradeStatus.SUGGEST);
        tradeRepository.save(trade);
        tradeScheduleRepository.save(new TradeSchedule(trade, tradeDate));
    }

    private void productStatusUpdate(Product product) {
        product.updateStatus(TradeStatus.SUGGEST);
        productRepository.save(product);
    }

    private void tradeProcess(Product product, Member seller, Member buyer, TradeRequest tradeRequest) {

        ischeckSelfProduct(product, seller);

        if (tradeRequest.isDirectStatus()) {
            Trade trade = directTradeService.purchase(product, buyer, seller, tradeRequest);

            saveTradeSchedule(trade, tradeRequest.getScheduleDate());
            productStatusUpdate(product);

        } else if (tradeRequest.isScheduleStatus()) {
            Trade trade = scheduleTradeService.purchase(product, buyer, seller, tradeRequest);

            saveTradeSchedule(trade, tradeRequest.getScheduleDate());
            productStatusUpdate(product);

        } else if (tradeRequest.isAuctionStatus()) {
            Trade trade = auctionTradeService.purchase(product, buyer, seller, tradeRequest);

            saveTradeSchedule(trade, tradeRequest.getScheduleDate());
            productStatusUpdate(product);

        } else {
            throw new IllegalArgumentException("거래 방식이 존재하지 않습니다.");
        }
    }

}
