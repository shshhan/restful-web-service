package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value={"password", "ssn"})    //value에 정의한 필드에 @JsonIgnore를 설정한 것과 같음.
@JsonFilter("UserInfo") //임의로 filterName 정의
public class User {
    private Integer id;

    //validation check
    //조건에 맞지 않으면 MethodArgumentNotValid Exception 발생
    @Size(min=2, message="Name은 두글자 이상 입력해주세요.")    //message 속성으로 bindingResult의 default message를 정의
    private String name;
    @Past
    private Date joinDate;

//    @JsonIgnore //@JsonIgnore를 설정한 필드는 클라이언트로 해당 필드를 전달되지 않음.
    private String password;
//    @JsonIgnore
    private String ssn;

}
