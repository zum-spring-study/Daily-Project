package com.week.zumgnmarket.endpoint.member.facade;

import com.week.zumgnmarket.endpoint.member.dto.MemberRequest;
import com.week.zumgnmarket.endpoint.member.dto.MemberResponse;
import com.week.zumgnmarket.service.member.MemberService;
import com.week.zumgnmarket.service.member.dto.MemberDTO;
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
