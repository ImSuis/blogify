package com.system.blogify.Controller;

import com.system.blogify.Pojo.UserPojo;
import com.system.blogify.Service.PostService;
import com.system.blogify.Service.UserService;
import com.system.blogify.entity.Post;
import com.system.blogify.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final PostService postService;
//    @GetMapping("/success")
//    public String success(Model model,Principal principal){
//        model.addAttribute("userdata",userService.findByEmail(principal.getName()));
//        List<Post> posts = postService.fetchAll();
//        model.addAttribute("posts", posts);
//        return "mk";
//    }
    @GetMapping("/create")
    public String createUser(Model model){
        model.addAttribute("user",new UserPojo());

        return "reg";
    }

    @PostMapping("/save")
    public String saveUser(@Valid UserPojo userPojo){
        userService.saveUser(userPojo);
        return "redirect:/user/login";
    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null||authentication instanceof AnonymousAuthenticationToken){
            return "login";
        }
        return "redirect:/user/profile";
    }

    @GetMapping("/profile")
    public String profile() {
        return "updateprofile";
    }

    @GetMapping("/profile/{id}")
    public String getUserProfiile(@PathVariable("id") Integer id, Model model ){
        User user = userService.fetchbyId(id);
        model.addAttribute("users", new UserPojo(user));
//        model.addAttribute("user", userServices.findByEmail(principal.getName()));
        model.addAttribute("currentUser", user);
        return "updateprofile";
    }
//
//
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model){
        User user =userService.fetchbyId(id);
        model.addAttribute("currentUser", new UserPojo(user));
        return "redirect:/user/profile/{id}";
    }
    @GetMapping("/{id}/delete")
    public String deleteProfile(@PathVariable("id") Integer id){
        userService.deleteById(id);
        return "redirect:/";
    }

//    @GetMapping("/profile")
//    public String getUserProfile (Integer id,Model model, Principal principal) {
//        model.addAttribute("update", new UserPojo());
//        model.addAttribute("info",userService.findByEmail(principal.getName()));
//        return "updateprofile";
//    }
//    @PostMapping("/profile/update")
//    public String updateUser(@Valid UserPojo userpojo) {
//        userService.update(userpojo);
//        return "redirect:/dashboard";
//    }

}




