package com.week.zumgnmarket.member.facade;

import com.week.zumgnmarket.member.dto.MemberRequest;
import com.week.zumgnmarket.member.dto.MemberResponse;
import com.week.zumgnmarket.member.service.MemberService;
import com.week.zumgnmarket.member.service.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberFacade {
    private final MemberService memberService;
    public MemberResponse signup(MemberRequest memberRequest) {
        MemberDTO member = memberService.signup(memberRequest);

        return MemberResponse.buildToResponse(member);
    }

}
