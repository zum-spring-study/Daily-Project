package com.week.zumgnmarket.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "town")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_idx")
    private Integer id;

    @Column(name = "region")
    private String region;

    public static Town of(String region) {
        return Town.builder()
                .region(region).build();
    }
}
