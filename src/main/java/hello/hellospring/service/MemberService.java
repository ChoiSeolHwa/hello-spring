package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Spring이 올라오때 service를 스프링이 컨테이너에 MemberService를 등록해
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    //Test랑 같은 인스턴스를 쓰게 하기위해서는 외부에서 넣어주게 바꾼다
    //MemberService입장에선 외부에서 주입하는것 : DI



    //회원가입
    public Long join(Member member) {
        //같은 이름이 있는 중복회원x
        //Optional<Member> result = memberRepository.findByName(member.getName());
        //위와 같이 바로 Optional로 나오면 모양이 안이쁘기때문에,,,
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();

    }

    //중복 회원 조회
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //ifPresent : null이 아니라 값이 있으면
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        //command+option+M
    }

    //전체회원조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
