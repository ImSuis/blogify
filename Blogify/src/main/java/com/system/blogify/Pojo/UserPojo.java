package com.system.blogify.Pojo;


import com.system.blogify.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserPojo {
    private Integer id;
    private String email;
    private  String mobile_no;
    private String password;
    private String fullname;


    public UserPojo(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.mobile_no = user.getMobileNo();
        this.fullname = user.getFullname();
        this.password = user.getPassword();

    }
}


