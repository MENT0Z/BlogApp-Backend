package com.nphck.demo.services;

import com.nphck.demo.model_entities.Post;
import com.nphck.demo.payloads.PostDTO;
import com.nphck.demo.payloads.PostResponse;

import java.util.List;

public interface PostService {
    //create
    PostDTO CreatePost(PostDTO postDTO,Integer catId,Integer Uid);
    // update
    PostDTO UpdatePost(PostDTO postDTO,Integer postId);
    //delete
    void deletePost(Integer postId);
    // getAll
    List<PostDTO>getAll();

    // get All By Paging
    PostResponse getAllPagin(Integer pageNumber, Integer pageSize,String sortBy,String sortDirn);
    // get by ele
    PostDTO getPostById(Integer postId);

    // get all post by categories
    List<PostDTO> getAllByCategory(Integer catId);

    // get All post by user
    List<PostDTO> getAllByUser(Integer uid);

    // to search post
    List<PostDTO> searchPosts(String Keyword);
}
