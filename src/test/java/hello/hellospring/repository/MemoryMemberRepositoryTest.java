package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

//갖다 안쓰니까 굳이 public 안해도됨
//실행순서는 무작위
//tdd : 테스트를 먼저 만들고 구현class 개발
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //실행 후 repository클리어
    //테스트끼리는 의존관계가 없어야하므로..
    @AfterEach
    public void  afterEach(){
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring"); //command+shift+enter

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //db,메모리랑 저장한값 비교
        //System.out.println("result = " + (result == member)); 매번 보는게 어려우니,assertions를 씀
        //Assertions.assertEquals(member, result);

        //
        assertThat(member).isEqualTo(result); //option + enter 치면 Assertions 생략 가능
                                              //import static org.assertj.core.api.Assertions.*; 얘 static으로 올라가서 생략가능해짐

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        //shift+f6
        //같은 이름이니까 member2 이름 바꾸면 밑에도 자동으로 바뀜
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

    }

    //전체 실행흘 하다보면 findAll에 저장된 객체와 findByName 에 저장된 객체가 다른 객체가 저장되어서
    //에러가 뜬다.. 그래서 한개의 메서드 테스트 실행후 초기화 해줘야됨
    //repository에서 초기화 메서드 하나 만들어준다.
}
