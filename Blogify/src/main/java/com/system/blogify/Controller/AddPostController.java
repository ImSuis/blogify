package com.system.blogify.Controller;

import com.system.blogify.entity.User;
import com.system.blogify.Pojo.PostPojo;
import com.system.blogify.Service.PostService;
import com.system.blogify.Service.UserService;
import com.system.blogify.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping()
public class AddPostController {
    private final PostService postService;
    private final UserService userService;



    @GetMapping("/add/{id}")
    public String getPost(@PathVariable("id") Integer id, Model model ){
        User user = userService.fetchbyId(id);
        model.addAttribute("postId",user);
        model.addAttribute("post", new PostPojo());
        return "newPost";
    }
}
