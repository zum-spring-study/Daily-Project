package com.week.zumgnmarket.domain;

import com.week.zumgnmarket.constant.TownType;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "region", nullable = false)
    private TownType region;

    public static Town of(TownType region) {
        return Town.builder()
                .region(region).build();
    }
}
