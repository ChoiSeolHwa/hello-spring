package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		// boot 는 tomcat 서버가 내장돼서 자체적으로 띄움
		// 인텔리제이가 gradle 을 통해서 실행할때 -> ctrl + alt + s -> build tools -> gradle -> build run using 및 run tests using 을 gradle 에서 intellij 로 변경
		// 바꾸는 이유는 gradle 을 통해서 실행하면 느림
		SpringApplication.run(HelloSpringApplication.class, args);

	}

}
