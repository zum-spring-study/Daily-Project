package com.week.zumgnmarket.town.entity;

import com.week.zumgnmarket.common.BaseEntity;
import com.week.zumgnmarket.product.entity.Product;
import com.week.zumgnmarket.enums.LocationNames;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "town")
public class Town extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "city_name", length = 30)
    private LocationNames cityName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    public Town(LocationNames cityName, Product product) {
        this.cityName = cityName;
        this.product = product;
    }
}
