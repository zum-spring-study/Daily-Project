package com.week.zumgnmarket.member;

import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.week.zumgnmarket.enums.LocationNames;
import com.week.zumgnmarket.member.dto.MemberRequest;
import com.week.zumgnmarket.member.dto.MemberResponse;
import com.week.zumgnmarket.member.facade.MemberFacade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@DisplayName("회원 관련 테스트")
@Transactional
public class MemberFacadeTest {

  @Autowired
  MemberFacade memberFacade;


  /*
    * 회원 가입 테스트
    * @RequestBody MemberRequest = 회원 가입 정보
    * @assert db에 저장된 값의 name과 memberRequest의 name이 같은지 확인
   */
  @Test
  @DisplayName("회원 가입 테스트")
  void signup() {
    MemberRequest memberRequest = new MemberRequest("테스트계정", LocationNames.SEOUL);

    MemberResponse signup = memberFacade.signup(memberRequest);

    // db에 저장된 값과 비교
    Assertions.assertEquals(signup.getMember().getName(), memberRequest.getName());
  }

}