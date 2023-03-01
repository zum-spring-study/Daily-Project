package com.week.zumgnmarket.service.member.dto;

import com.week.zumgnmarket.entity.Member;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Member member;

    @Getter
    @Builder
    public static class Member {
        private Long id;
        private String name;
        private String location;
        private Boolean activate;
        private LocalDate createdAt;
        private LocalDate updatedAt;

        public static Member of(com.week.zumgnmarket.entity.Member member) {
            return Member.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .location(member.getLocation().toString())
                    .activate(member.getActivate())
                    .createdAt(member.getCreatedDate())
                    .updatedAt(member.getLastModifiedDate())
                    .build();
        }
    }

    public static MemberDTO buildToDTO(Member member) {
        return MemberDTO.builder()
                .member(member)
                .build();
    }
}
