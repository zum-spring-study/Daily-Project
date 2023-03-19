package com.week.zumgnmarket.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
@Table(name = "buyer")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_idx")
    private Integer id;

    @Column(name = "nick_name")
    private String nickName;

    public static Buyer of(String nickName) {
        return Buyer.builder()
                .nickName(nickName).build();
    }

}
