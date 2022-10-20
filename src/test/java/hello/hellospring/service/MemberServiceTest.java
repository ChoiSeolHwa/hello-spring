package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


//command+shift+t
//test 자동으로 만들어짐
class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //아무래도 new 로 새로 생성한 인스턴스기 때문에 안에 속해있는 내용은 달라질수있음
    //MemberService랑 Test랑 결국 다른것..

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        //Test는 독립적으로 실행해야되니깐 테스트실행전에 실행해서 인스턴스 생성함
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
        //메모리 클리어
    }

    @Test
    void 회원가입() {
        //test는 과감하게 한글로 바꿔도됨
        //build될때 test는 제외됨


        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외 (){
        //givne
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
//        memberService.join(member1);
//        memberService.join(member2);
        //중복이기 때문에 Exception에서 잡혀야됨

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));//command+option+v

        assertThat(e.getMessage()).isEqualToIgnoringNewLines("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}