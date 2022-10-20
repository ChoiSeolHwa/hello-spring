package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.sql.Array;
import java.util.*;
@Repository
public class MemoryMemberRepository implements MemberRepository {
    //implements method = ctrl + i / option + enter

    private static Map<Long,Member> store = new HashMap<>(); //저장,예제니까 단순히 hashMap
    private static Long sequence = 0L; //seq생성 (1,2,3~)

    @Override
    public Member save(Member member) {
        //seq 하나 올려주고
        member.setId(++sequence);
        //store하기 전에 seq 세팅
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //id가 null이어도 감싸서 반환, 그걸 방어하기 위해 optional 씀
    }

    @Override
    public Optional<Member> findByName(String name) {

        //람다
        //loof
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); //하나만이라도 찾으면 반환함.optional 없으면 null반환
    }

    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values()); //member들이 반환됨
    }

    //==> 얘네들이 동작하는지 아닌지는 testCase 작성으로 확인

    public void clearStore(){
        store.clear();
    }
}
