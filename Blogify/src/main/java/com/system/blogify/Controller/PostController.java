package com.system.blogify.Controller;

import com.system.blogify.Pojo.PostPojo;
import com.system.blogify.Pojo.UserPojo;
import com.system.blogify.Service.PostService;
import com.system.blogify.Service.UserService;
import com.system.blogify.entity.Post;
import com.system.blogify.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final UserService userService;

    private final PostService postService;
    @GetMapping("/posts/new")
    public String createUser(Model model){
        model.addAttribute("post",new PostPojo());

        return "newpost";
    }
//
//    @GetMapping("/posts/new")
//    @PreAuthorize("isAuthenticated()")
//    public String createNewPost(Model model, Principal principal) {
//
//        String authUsername = "anonymousUser";
//        if (principal != null) {
//            authUsername = principal.getName();
//        }
//
//        Optional<User> optionalUser = Optional.ofNullable(userService.findByEmail(authUsername));
//        if (optionalUser.isPresent()) {
//            Post post = new Post();
//            post.setUser(optionalUser.get());
//            model.addAttribute("post", post);
//            return "newpost";
//        } else {
//            return "redirect:/";
//        }
//    }
//
//    @PostMapping("/posts/new")
//    @PreAuthorize("isAuthenticated()")
//    public String createNewPost(@ModelAttribute Post post, Principal principal) {
//        String authUsername = "anonymousUser";
//        if (principal != null) {
//            authUsername = principal.getName();
//        }
//        if (post.getUser().getEmail().compareToIgnoreCase(authUsername) < 0) {
//            // TODO: some kind of error?
//            // our account email on the Post not equal to current logged in account!
//        }
//        postService.savePost(new PostPojo());
//        return "redirect:/posts/" + post.getId();
//    }

//    @PostMapping("/posts/new")
//    public String saveProduct(@Valid PostPojo postPojo, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {
//
//        Map<String, String> requestError = validateRequest(bindingResult);
//        if (requestError != null) {
//            redirectAttributes.addFlashAttribute("requestError", requestError);
//            return "redirect:/posts/list";
//        }
//
//        postService.savePost(postPojo);
//        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");
//
//
//        return "mk";
//    }
//
//
    @PostMapping("/save-post")
    public String saveProduct(@Valid PostPojo postPojo, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws IOException {

        Map<String, String> requestError = validateRequest(bindingResult);
        if (requestError != null) {
            redirectAttributes.addFlashAttribute("requestError", requestError);
            return "redirect:/";
        }

        postService.savePost(postPojo);
        redirectAttributes.addFlashAttribute("successMsg", "User saved successfully");


        return "redirect:/";
    }

    public Map<String, String> validateRequest(BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            return null;
        }
        Map<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return errors;

    }

    @GetMapping("/posts/{id}")
    public String getPost(@PathVariable("id") Integer id, Model model ){
        Post post = postService.fetchById(id);
        model.addAttribute("post", new PostPojo(post));
        return "post";
    }

    @GetMapping("/posts/{id}/edit")
    public String ePost(@PathVariable("id") Integer id, Model model ){
        Post post = postService.fetchById(id);
        model.addAttribute("post", new PostPojo(post));
        return "post_edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(@Valid PostPojo postPojo){
        Post post = postService.fetchById((postPojo.getId()));
        post.setBody(postPojo.getBody());
        post.setTitle(postPojo.getTitle());
        postService.savePost(postPojo);
//        model.addAttribute("currentUser", new UserPojo(user));
        return "redirect:/posts/{id}";
    }

    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable("id") Integer id){
        postService.deletePost(id);
        return "redirect:/";
    }
}
