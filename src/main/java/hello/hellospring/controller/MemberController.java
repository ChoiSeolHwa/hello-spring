package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    //Controller를 선언해주면 객체생성해서 스프링컨테이너안에 넣고 스프링에서 관리해줌

    //여기저기서 갖다 쓸수있음
    //private final MemberService memberService = new MemberService();

    //컨테이너에 등록해서 쓰면됨
    private final MemberService memberService;

    //memberService와 연결해줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
