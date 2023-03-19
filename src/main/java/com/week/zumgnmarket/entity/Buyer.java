package com.week.zumgnmarket.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "buyer")
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "buyer_idx")
    private Integer id;

    @Column(name = "nick_name")
    private String nickName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "musical_idx")
    private List<Musical> musicals = new ArrayList<Musical>();

    public void updateMusicals(Musical musical) {
        this.musicals.add(musical);
    }

    public boolean isExistMusicals(Musical musical) {
        return this.musicals.contains(musical);
    }

}
