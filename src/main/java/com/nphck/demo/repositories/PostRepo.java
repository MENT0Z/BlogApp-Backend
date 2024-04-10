package com.nphck.demo.repositories;

import com.nphck.demo.model_entities.Categories;
import com.nphck.demo.model_entities.Post;
import com.nphck.demo.model_entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
    List<Post>findByUser(User user);
    List<Post>findByCategories(Categories categories);
    // here we can use any like for title we use findByTitleContaining and for content findByContentContaining and so on
            // to find imageName
    //  List<Post>findByImagenameContaining(String imageName);
    List<Post>findByTitleContaining(String title);
}
