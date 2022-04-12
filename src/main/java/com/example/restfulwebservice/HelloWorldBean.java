package com.example.restfulwebservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //getter, setter, equals, hasCode, toString
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
@NoArgsConstructor //매개변수 없는 기본 생성자
public class HelloWorldBean {
    private String message;

}
