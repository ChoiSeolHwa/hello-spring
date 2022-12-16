package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller//component scan
public class MemberController {
    //Controller를 선언해주면 객체생성해서 스프링컨테이너안에 넣고 스프링에서 관리해줌

    //여기저기서 갖다 쓸수있음
    //private final MemberService memberService = new MemberService();

    //컨테이너에 등록해서 쓰면됨
    private final MemberService memberService;
    //@Autowired private MemberService memberService; //필드주입(별로임)

    //setter주입은 public으로 올라와서 아무 개발자가 호출할수가 있음
    //조립시점에 생성자 만들어놓고 변경할수 없게 막아버려야됨
     

    //memberService와 연결해줌
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return"members/createMembersForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return"redirect:/";
    }
    @GetMapping("/members") //메모리에 저장된거라 서버내리면 다 날라감
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }

}
