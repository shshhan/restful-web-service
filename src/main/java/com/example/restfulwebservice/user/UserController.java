package com.example.restfulwebservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        List<User> users = service.findAll();

        return users;
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {    //파라미터는 기본적으로 String으로 들어오지만 자동으로 형변환 되어 들어온다
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return user;
    }

    @PostMapping("/users")  //동일 경로 메서드가 있지만 http method가 다르기 때문에 사용 가능
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) { //객체의 validation 기능 활성화
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

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }

    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody User user, @PathVariable int id){
        User one = service.updateName(user, id);

        if(one == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", user.getId()));
        }

    }

}
