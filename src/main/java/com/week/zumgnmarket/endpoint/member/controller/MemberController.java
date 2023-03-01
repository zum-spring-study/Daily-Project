package com.week.zumgnmarket.endpoint.member.controller;


import com.week.zumgnmarket.endpoint.member.dto.MemberRequest;
import com.week.zumgnmarket.endpoint.member.dto.MemberResponse;
import com.week.zumgnmarket.endpoint.member.facade.MemberFacade;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Member", description = "회원 관련 API")
public class MemberController {

    private final MemberFacade memberFacade;

    /*
        * 회원 가입
        * @RequestBody MemberRequest = 회원 가입 정보
        * @return MemberResponse
     */
    @PostMapping("/member/signup")
    @Tag(name = "signup", description = "회원 가입 API")
    public MemberResponse signup(@RequestBody MemberRequest memberRequest) {
        return memberFacade.signup(memberRequest);
    }

}
