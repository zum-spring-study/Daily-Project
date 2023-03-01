package com.week.zumgnmarket.entity;

import com.week.zumgnmarket.enums.LocationNames;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_location", nullable = false)
    private LocationNames location;

    // 멤버의 활성화 여부
    @Column(nullable = false)
    private Boolean activate;

    public Member(String name, LocationNames location, Boolean activate) {
        this.name = name;
        this.location = location;
        this.activate = activate;
    }
}
