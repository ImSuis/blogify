package com.system.blogify.Controller;

import com.system.blogify.Service.PostService;
import com.system.blogify.Service.UserService;
import com.system.blogify.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final PostService postService;
    private final UserService userService;
    @GetMapping("/")
    public String success(Model model, Principal principal){
        model.addAttribute("userdata",userService.findByEmail(principal.getName()));
        List<Post> posts = postService.fetchAll();
        model.addAttribute("posts", posts);
        return "home";
    }

    @PostMapping("/logout")
    public String logout(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            SecurityContextHolder.clearContext();
        }
        return "redirect:/user/login";
    }
}
