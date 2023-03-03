package com.week.zumgnmarket.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Integer id;

    @Column(name = "nick_name")
    private String nickName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "town_idx")
    private Town town;

}
