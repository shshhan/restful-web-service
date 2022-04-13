package com.example.restfulwebservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//http status code
//2xx : OK
//4xx : Client fault
//5xx : Server fault
@ResponseStatus(HttpStatus.NOT_FOUND)   //not found error 발생
//ExceptionHandler가 해당 excepion에 대해 처리 후 return시 http status를 정의한다면 @ResponseStatus가 정의한 http status는 무시된다. 어노테이션이 없어도 상관없다.
// => 해당 Exception 객체를 return시 http status가 함께 정의된다면 @ResponseStatus 필요X. 따로 http status가 정의되지 않는다면 필요!
public class UserNotFoundException extends RuntimeException {   //RuntimeException : 실행시 발생하는 exception
    public UserNotFoundException(String message) {
        super(message);
    }
}
