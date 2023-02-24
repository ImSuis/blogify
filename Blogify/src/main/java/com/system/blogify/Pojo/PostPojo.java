package com.system.blogify.Pojo;

import com.system.blogify.entity.Post;
import com.system.blogify.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostPojo {
    private Integer id;
    private String title;
    private String body;
    private LocalDateTime createdAt;
    private  LocalDateTime updatedAt;

    private User user;

//    public PostPojo(Post post){
//        this.id = post.getId();
//        this.title = post.getTitle();
//        this.body = post.getBody();
//        this.createdAt = post.getCreatedAt();
//        this.updatedAt = post.getUpdatedAt();
//        this.user = post.getUser().getId();
//    }
}
