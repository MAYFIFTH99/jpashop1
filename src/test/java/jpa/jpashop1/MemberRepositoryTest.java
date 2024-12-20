package jpa.jpashop1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void testMember() throws Exception {
        //given
        Member member = new Member();
        Long savedId = memberRepository.save(member);

        //when
        Member findMember = memberRepository.find(savedId);
        //then
        assertThat(findMember).isEqualTo(member);
    }

}