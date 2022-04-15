package com.example.restfulwebservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private Integer id;

    //validation check
    //조건에 맞지 않으면 MethodArgumentNotValid Exception 발생
    @Size(min=2, message="Name은 두글자 이상 입력해주세요.")    //message 속성으로 bindingResult의 default message를 정의
    private String name;
    @Past
    private Date joinDate;

}
