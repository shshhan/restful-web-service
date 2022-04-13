package com.example.restfulwebservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {    //파라미터는 기본적으로 String으로 들어오지만 자동으로 형변환 되어 들어온다
        return service.findOne(id);
    }

    @PostMapping("/users")  //동일 경로 메서드가 있지만 http method가 다르기 때문에 사용 가능
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = service.save(user);

        //URI 만들기
        URI location = ServletUriComponentsBuilder.fromCurrentRequest() //현재의 request URI
                .path("/{id}")  //현재 URI 뒤에 붙일 경로 추가. {}로 가변변수 사용
                .buildAndExpand(savedUser.getId())  //가변변수에 넣을 값 세팅
                .toUri(); //URI 타입으로 변환

        return ResponseEntity.created(location).build();
        //http status 201 반환
        //Response Headers에 key:Location, value:생성한 uri(==location)가 추가됨
    }
}
