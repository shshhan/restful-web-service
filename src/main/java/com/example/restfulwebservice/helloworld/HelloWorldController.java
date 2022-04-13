package com.example.restfulwebservice.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    // GET
    // /hello-world (endPoint)
    // == @RequestMapping(method = RequestMethod.GET, path="hello-world")
    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
        //RestController를 사용하면 String이 아닌 Bean 형태로 return하면 json으로 변환해서 리턴
        //컬렉션 프레임워크, 배열 등을 리턴할 때도 마찬가지로 json으로 변환해서 리턴
    }

    //Path Variable
    //URI에 사용하는 가변 변수
    //클라이언트에서 가변 데이터를 변경해서 요청 가능
    @GetMapping("/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable(value = "name") String name){    //만약 pathVariable의 이름과 매개변수의 이름이 다르면 value속성으로 pathVariable의 이름을 지정
        return new HelloWorldBean(String.format("Hello World, %s", name));
        //String.format(표현하고자 하는 문자열 형태, 가변 데이터의 값)
    }

}
