package com.system.blogify.Service.Impl;

import com.system.blogify.Pojo.PostPojo;
import com.system.blogify.Pojo.UserPojo;
import com.system.blogify.Repo.PostRepo;
import com.system.blogify.Repo.UserRepo;
import com.system.blogify.Service.PostService;
import com.system.blogify.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;

    private final UserRepo userRepo;

    public PostPojo savePost(PostPojo postPojo)  {
        Post post = new Post();
        if(postPojo.getId()!=null){
            post.setId(postPojo.getId());
        }
        post.setId(postPojo.getId());
        post.setTitle(postPojo.getTitle());
        post.setBody(postPojo.getBody());
        post.setCreatedAt(postPojo.getCreatedAt());
        post.setUpdatedAt(postPojo.getUpdatedAt());
//        post.setUser(userRepo.findById(postPojo.getUser()).orElseThrow());

        postRepo.save(post);
        return new PostPojo(post);
    }

    @Override
    public List<Post> fetchAll() {
        return this.postRepo.findAll();
    }

    @Override
    public Post fetchById(Integer id){
        return postRepo.findById(id).orElseThrow(()-> new RuntimeException("not found"));
    }


    @Override
    public String deletePost(Integer id) {
        postRepo.deleteById(id);
        return "Deleted";
    }

//    @Override
//    public Post fetchById(Integer id) {
//        Post post= postRepo.findById(id).orElseThrow(()-> new RuntimeException("CouldNot Find"));
////        Product product = productRepo.findById(order.getOrderId()).orElseThrow(()-> new RuntimeException("CouldNot Find"));
//
//        return post;
//    }
}
