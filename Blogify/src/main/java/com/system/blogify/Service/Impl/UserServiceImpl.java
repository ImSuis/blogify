package com.system.blogify.Service.Impl;
import com.system.blogify.Pojo.UserPojo;
import com.system.blogify.Repo.UserRepo;
import com.system.blogify.Service.UserService;
import com.system.blogify.entity.User;
import com.system.blogify.exception.AppException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public String saveUser(UserPojo userPojo){
        User user =new User();
        if(userPojo.getId()!=null){
            user.setId(userPojo.getId());
        }
        user.setFullname(userPojo.getFullname());
        user.setEmail(userPojo.getEmail());
        user.setMobileNo(userPojo.getMobile_no());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePassword = passwordEncoder.encode(userPojo.getPassword());
        user.setPassword(encodePassword);

        userRepo.save(user);
        return "created";
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new AppException("Invalid User email", HttpStatus.BAD_REQUEST));
        return user;
    }
    @Override
    public List<User> fetchAll() {
        return this.userRepo.findAll();

    }
    @Override
    public User fetchbyId(Integer id){
        return userRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
    }

    @Override
    public void deleteById(Integer id) {
        userRepo.deleteById(id);

    }


}
