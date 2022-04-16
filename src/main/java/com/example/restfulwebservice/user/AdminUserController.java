package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {
    private final UserDaoService service;

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {  //filtering 되어 있는 데이터를 반환하기 위해서는 일반적인 도메인 객체가 아닌 MappingJacksonValue를 리턴
        List<User> users = service.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "joinDate", "password", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }

//    @GetMapping("/v1/users/{id}") //주소를 통한 버전관리
//    @GetMapping(value="/users/{id}", params="version=1")   //request parmeter를 통한 버전관리
//    @GetMapping(value="/users/{id}", headers="X-API-VERSION=1")   //request header를 통한 버전관리
    @GetMapping(value="/users/{id}", produces="application/vnd.company.appv1+json")   //MIME type을 통한 버전관리
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {    //파라미터는 기본적으로 String으로 들어오지만 자동으로 형변환 되어 들어온다
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "joinDate", "password", "ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }

//    @GetMapping("/v2/users/{id}")
//    @GetMapping(value="/users/{id}", params="version=2")
//    @GetMapping(value="/users/{id}", headers="X-API-VERSION=2")
    @GetMapping(value="/users/{id}", produces="application/vnd.company.appv2+json")   //MIME type을 통한 버전관리
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {    //파라미터는 기본적으로 String으로 들어오지만 자동으로 형변환 되어 들어온다
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        //반환받은 User 객체를 User2로 변환
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2);
        userV2.setGrade("VIP");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }
    }
