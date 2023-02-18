package com.system.blogify.Service;

import com.system.blogify.Pojo.PostPojo;
import com.system.blogify.entity.Post;

import java.util.List;

public interface PostService{
    PostPojo savePost(PostPojo postPojo);

    Post fetchById(Integer id);

    List<Post> fetchAll();

    String deletePost(Integer id);
}
