package com.week.zumgnmarket.domain;

import com.week.zumgnmarket.application.dto.UserRequest;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
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

    public static User of(UserRequest userDto, Town town) {
        return User.builder()
                .nickName(userDto.getNickName())
                .town(town)
                .build();
    }
}
