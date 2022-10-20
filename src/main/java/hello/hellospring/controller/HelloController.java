package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello spring!");

        return "hello"; //templates/hello.html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name",required = false) String name, Model model) {
        //required=false 는 값을 꼭 넘기지 않아도됨
        //기본은 true
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http에서 body 부에 "hello " + name; 해당 데이타를 직접 넣어주겠다라는 뜻
    public String helloString(@RequestParam("name") String name) {
        //그래서 html 소스 보기 하면 html 태그들이 안나오고 그냥 문자들만  나옴
        return "hello " + name; //hello spring
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        //{"name":"메롱"} json 방식으로 출력
        return hello;
    }
    static class Hello {
        private String name;

        //getter,setter 단축기
        //프로퍼티 접근방식
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
