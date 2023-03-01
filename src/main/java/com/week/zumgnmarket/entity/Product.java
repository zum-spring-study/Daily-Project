package com.week.zumgnmarket.entity;

import com.week.zumgnmarket.enums.TradeStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "trade_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_name", length = 20)
    private String name;

    // 등록한 유저
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "product_description", length = 100)
    private String description;

    @Column(name = "product_price", length = 20)
    private Long price;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status", length = 20)
    private TradeStatus status;

    public Product(String name, String description, Long price, Member member, TradeStatus status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.member = member;
        this.status = status;
    }

    public void update(String name, String description, Long price, TradeStatus status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
    }

    public void updateStatus(TradeStatus status) {
        this.status = status;
    }
}
