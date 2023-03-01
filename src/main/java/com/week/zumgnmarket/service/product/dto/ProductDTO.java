package com.week.zumgnmarket.service.product.dto;

import com.week.zumgnmarket.enums.LocationNames;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Product product;

    @Getter
    @Builder
    public static class Product {
        private Long id;
        private String memberName;
        private String name;
        private String description;
        private Long price;
        private LocationNames townName;
        private LocalDate createdAt;
        private LocalDate updatedAt;

        public static Product of (com.week.zumgnmarket.entity.Product product, LocationNames town) {
            return Product.builder()
                    .id(product.getId())
                    .memberName(product.getMember().getName())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .townName(town)
                    .createdAt(product.getCreatedDate())
                    .updatedAt(product.getLastModifiedDate())
                    .build();
        }
    }

    public static ProductDTO buildToDTO(Product product) {
        return ProductDTO.builder()
                .product(product)
                .build();
    }
}

