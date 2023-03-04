package com.week.zumgnmarket.member.service;

import com.week.zumgnmarket.member.dto.MemberRequest;
import com.week.zumgnmarket.member.entity.Member;
import com.week.zumgnmarket.member.repository.MemberRepository;
import com.week.zumgnmarket.member.service.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberDTO signup(MemberRequest memberRequest) {
        Member member = new Member(memberRequest.getName(), memberRequest.getLocation(), true);
        memberRepository.save(member);

        return MemberDTO.buildToDTO(MemberDTO.Member.of(member));
    }

}
