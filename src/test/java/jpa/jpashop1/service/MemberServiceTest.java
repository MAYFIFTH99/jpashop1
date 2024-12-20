package jpa.jpashop1.service;

import jpa.jpashop1.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;


    @DisplayName("회원 가입")
    @Test
    void join() {
        //given
        Member member = new Member();
        memberService.join(member);

        //when
        Member findMember = memberService.findById(member.getId());

        //then
        assertThat(findMember).isEqualTo(member);
    }


    @Test(expected = IllegalStateException.class)
    @DisplayName("중복 이름 오류")
    void joinException() throws Exception {
        //given
        Member member = new Member("member1");
        Member sameMember = new Member("member1");

        //when
        memberService.join(member);
        //then
        assertThatThrownBy(() -> memberService.join(sameMember));
        fail();
    }
}