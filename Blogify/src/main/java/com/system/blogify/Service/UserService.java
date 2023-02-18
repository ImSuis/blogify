package com.system.blogify.Service;

import com.system.blogify.Pojo.UserPojo;
import com.system.blogify.entity.User;

import java.util.List;

public interface UserService {
    String saveUser(UserPojo userPojo);
    List<User> fetchAll();
    User findByEmail(String email);
    User fetchbyId(Integer id);
    void deleteById(Integer id);
}