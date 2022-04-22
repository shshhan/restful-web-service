package com.example.restfulwebservice.post;

import com.example.restfulwebservice.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private int id;

    private String description;

    //User : Post => 1 : (0~N), Main : Sub
    //여러 Post 객체가 하나의 User 객체와 매핑,
    // FetchType.LAZY : 지연로딩방식. User Entity 조회 시 매번 Post Entity가 로딩되지 않고, Post Entity를 로딩할 때 필요한 User 객체를 로딩함.
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
}


