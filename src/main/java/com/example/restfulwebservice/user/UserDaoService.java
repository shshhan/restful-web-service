package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

//이번 예제에서는 실제 RDB에 연결하지 않을 예정이기 때문에 DAO와 Service를 하나로 사용하기 위한 클래스
@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    //DB에 아래의 값이 들어가있다고 가정
    static{
        users.add(new User(1, "Shawn", new Date(), "pass1", "950110-1111111"));
        users.add(new User(2, "Jack", new Date(), "pass2", "950110-2111111"));
        users.add(new User(3, "John", new Date(), "pass3", "960110-1111111"));
    }

    private static int usersCount = users.size();   // 새로 ID를 부여해주기 위한 변수

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id){
        for(User user : users){
            if(user.getId() == id){
                return user;
            }
        }

        return null;

    }

    public User save(User user){
        if(user.getId() == null){
            user.setId(++usersCount);
        }

        users.add(user);

        return user;
    }

    public User deleteById(int id){
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()){
            User user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }

        return null;

    }

    public User updateName(User user, int id){
        Iterator<User> iterator = users.iterator();

        User one = this.findOne(id);

        if(one != null){
            one.setName(user.getName());
            return one;
        }

        return null;

    }

}
