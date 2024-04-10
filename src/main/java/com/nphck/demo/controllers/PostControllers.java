package com.nphck.demo.controllers;

import com.nphck.demo.config.AllConstants;
import com.nphck.demo.payloads.ApiResponse;
import com.nphck.demo.payloads.PostDTO;
import com.nphck.demo.payloads.PostResponse;
import com.nphck.demo.services.CategoryService;
import com.nphck.demo.services.PostService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostControllers {

    @Autowired
    private PostService postService;

    // create
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO>CreatePost(
            @RequestBody PostDTO postDTO,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId){
        PostDTO creatPost = this.postService.CreatePost(postDTO,categoryId,userId);
        return new ResponseEntity<>(creatPost, HttpStatus.CREATED);
    }

    // update
    @PutMapping("posts/{postId}")
    public ResponseEntity<PostDTO>UpdatePost(@RequestBody PostDTO postDTO,@PathVariable Integer postId){
        PostDTO updatedPost = this.postService.UpdatePost(postDTO,postId);
        return ResponseEntity.ok(updatedPost);
    }


    // delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return new ApiResponse("Post Deleted sucessfully",true);
    }

    // get post by postId
    @GetMapping("/post/{postId}/posts")
    public ResponseEntity<PostDTO>getPostById(@PathVariable Integer postId){
        PostDTO postDTO = this.postService.getPostById(postId);
        return  ResponseEntity.ok(postDTO);
    }

    // get post by category
    @GetMapping("/category/{catId}/posts")
    public ResponseEntity<List<PostDTO>>getAllPostsByCategory(@PathVariable Integer catId){
        List<PostDTO>postDTOS = this.postService.getAllByCategory(catId);
        return new ResponseEntity<>(postDTOS,HttpStatus.OK);
    }


    // get post by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>>getAllPostsByUser(@PathVariable Integer userId){
        return (new ResponseEntity<>(this.postService.getAllByUser(userId),HttpStatus.OK));
    }

    // get all posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>>getAllPosts()
    {
        return new ResponseEntity<>(this.postService.getAll(),HttpStatus.OK);
    }

    // get all posts by paging
    @GetMapping("/posts/page")
    public ResponseEntity<PostResponse>getAllPostByPaging(
            @RequestParam(value = "pageNumber",defaultValue = AllConstants.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AllConstants.PAGE_SIZE,required = false)Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AllConstants.SORT_BY,required = false)String sortBy,
            @RequestParam(value = "sortDirn",defaultValue = AllConstants.SORT_DIRN,required = false)String sortDirn
    ){
       PostResponse postResponse = this.postService.getAllPagin(pageNumber,pageSize,sortBy,sortDirn);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    // search post
    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDTO>>searchPost(@PathVariable String keyword){
        List<PostDTO>postDTOS = this.postService.searchPosts(keyword);
        return new ResponseEntity<>(postDTOS,HttpStatus.OK);
    }



}
