package com.example.restfulwebservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //@Repository 어노테이션과, extends JpaRepository<객체 타입, pk타입>으로 Spring Data JPA 사용 가능


}
