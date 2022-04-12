package com.example.restfulwebservice;

import org.aspectj.weaver.World;
import org.springframework.web.bind.annotation.GetMapping;
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

}
