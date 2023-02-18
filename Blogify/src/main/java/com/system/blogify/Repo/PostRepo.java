package com.system.blogify.Repo;

import com.system.blogify.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post,Integer> {

}
