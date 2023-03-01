package com.week.zumgnmarket.endpoint.product.dto;

import com.week.zumgnmarket.enums.LocationNames;
import com.week.zumgnmarket.service.product.dto.ProductDTO;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;
    private String memberName;
    private String name;
    private String description;
    private Long price;
    private LocationNames townName;
    private LocalDate createdAt;
    private LocalDate updatedAt;


    public static ProductResponse buildToResponse(ProductDTO product) {
        return ProductResponse.builder()
                .id(product.getProduct().getId())
                .memberName(product.getProduct().getMemberName())
                .name(product.getProduct().getName())
                .description(product.getProduct().getDescription())
                .price(product.getProduct().getPrice())
                .townName(product.getProduct().getTownName())
                .createdAt(product.getProduct().getCreatedAt())
                .updatedAt(product.getProduct().getUpdatedAt())
                .build();
    }
}
