package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
     //저장소가 아직 정해지지 않았으니 interface로 만들어짐
     Member save(Member member);
     Optional<Member> findById(Long id);
     Optional<Member> findByName(String name);
     List<Member> findAll();
}
