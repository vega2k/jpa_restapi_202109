package jpastudy.jpashop.service;

import jpastudy.jpashop.domain.Member;
import jpastudy.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Test @Disabled
    public void 회원가입() throws Exception {
    //Given
        Member member = new Member();
        member.setName("boot");
    //When
        Long saveId = memberService.join(member);
    //Then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test
    @DisplayName("중복된회원가입시 실패")
    public void 중복회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("boot");
        Member member2 = new Member();
        member2.setName("boot");

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member1);
            memberService.join(member2); //예외 발생함
        });

        assertEquals("이미 존재하는 회원입니다.", exception.getMessage());
    }
}