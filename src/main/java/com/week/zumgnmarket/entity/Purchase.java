package com.week.zumgnmarket.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_idx")
    private Buyer buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "musical_idx")
    private Musical musical;

    public static Purchase of(Buyer buyer, Musical musical) {
        return Purchase.builder()
                .buyer(buyer)
                .musical(musical)
                .build();
    }

    public Integer getBuyerId() {
        return this.buyer.getId();
    }

    public String getTitle() {
        return this.musical.getTitle();
    }
}
