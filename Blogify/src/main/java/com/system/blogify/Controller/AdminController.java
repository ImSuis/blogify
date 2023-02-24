package com.system.blogify.Controller;

import com.system.blogify.Pojo.UserPojo;
import com.system.blogify.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.system.blogify.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("user",new UserPojo());
        List<User> users = userService.fetchAll();
        model.addAttribute("users", users);
        return "admin";
    }


//    @GetMapping("/delete/{id}")
//    public String deleteuser(@PathVariable("id") Integer id){
//        userService.deleteById(id);
//        return "redirect:/admin";
//    }
//    @GetMapping("/list")
//    public String getproductList(Model model){
//        List<User> adminuser=userService.fetchAll();
//        model.addAttribute("user", adminuser.stream().map(user ->
//                User.builder()
//                        .id(user.getId())
//                        .fullname(user.getFullname())
//                        .email(user.getEmail())
//                        .mobileNo(user.getMobileNo())
//                        .build()
//
//        ));
//        return "admin";
//    }
}
