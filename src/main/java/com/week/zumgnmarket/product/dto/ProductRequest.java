package com.week.zumgnmarket.product.dto;

import com.week.zumgnmarket.member.entity.Member;
import com.week.zumgnmarket.product.entity.Product;
import com.week.zumgnmarket.enums.LocationNames;
import com.week.zumgnmarket.enums.TradeStatus;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String memberName;
    private String productName;
    private String description;
    private LocationNames town;
    private Long price;
    private TradeStatus tradeStatus;

    public Product toEntity(Member member, ProductRequest productRequest) {
        return new Product(productRequest.getProductName(), productRequest.getDescription(), productRequest.getPrice(), member, productRequest.getTradeStatus());
    }

}
