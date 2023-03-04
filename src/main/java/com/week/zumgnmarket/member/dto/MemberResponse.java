package com.week.zumgnmarket.member.dto;

import com.week.zumgnmarket.member.service.dto.MemberDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {

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

        public static Member buildToResponse(MemberDTO memberDTO) {
            return Member.builder()
                    .id(memberDTO.getMember().getId())
                    .name(memberDTO.getMember().getName())
                    .location(memberDTO.getMember().getLocation())
                    .activate(memberDTO.getMember().getActivate())
                    .createdAt(memberDTO.getMember().getCreatedAt())
                    .updatedAt(memberDTO.getMember().getUpdatedAt())
                    .build();
        }
    }

    public static MemberResponse buildToResponse(MemberDTO memberDTO) {
        return MemberResponse.builder()
                .member(Member.buildToResponse(memberDTO))
                .build();
    }


}
