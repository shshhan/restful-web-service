package com.example.restfulwebservice.user;

import com.example.restfulwebservice.post.Post;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value={"password", "ssn"})    //value에 정의한 필드에 @JsonIgnore를 설정한 것과 같음.
//@JsonFilter("UserInfo") //임의로 filterName 정의
@Entity     //해당 클래스로 DB에 테이블 생성
public class User {

    @Id     //해당 컬럼을 key로 설정
    @GeneratedValue //값 자동생성
    private Integer id;

    //validation check
    //조건에 맞지 않으면 MethodArgumentNotValid Exception 발생
    @Size(min=2, message="Name은 두글자 이상 입력해주세요.")    //message 속성으로 bindingResult의 default message를 정의
    @ApiModelProperty(notes = "사용자 이름을 입력해주세요.")
    private String name;

    @Past
    @ApiModelProperty(notes = "사용자 등록일을 입력해주세요.")
    private Date joinDate;

//    @JsonIgnore //@JsonIgnore를 설정한 필드는 클라이언트로 해당 필드를 전달되지 않음.
    @ApiModelProperty(notes = "사용자 패스워드를 입력해주세요.")
    private String password;
//    @JsonIgnore
    private String ssn;

    @OneToMany(mappedBy = "user")  //하나의 User 객체가 여러 Post 객체가 매핑
    private List<Post> posts;

    public User(int id, String name, Date date, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = date;
        this.password = password;
        this.ssn = ssn;
    }

}
