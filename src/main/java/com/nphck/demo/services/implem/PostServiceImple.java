package com.nphck.demo.services.implem;

import com.nphck.demo.exceptions.ResourceNotFoundException;
import com.nphck.demo.model_entities.Categories;
import com.nphck.demo.model_entities.Post;
import com.nphck.demo.model_entities.User;
import com.nphck.demo.payloads.PostDTO;
import com.nphck.demo.payloads.PostResponse;
import com.nphck.demo.repositories.CategoryRepo;
import com.nphck.demo.repositories.PostRepo;
import com.nphck.demo.repositories.Userepo;
import com.nphck.demo.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PostServiceImple implements PostService {

    @Autowired
    public PostRepo postRepo;
    @Autowired
    public ModelMapper modelMapper;
    @Autowired
    public CategoryRepo categoryRepo;
    @Autowired
    public Userepo userepo;


    @Override
    public PostDTO CreatePost(PostDTO postDTO, Integer catid, Integer uid) {
        User user = this.userepo.findById(uid).orElseThrow(()->new ResourceNotFoundException("User","UserId",uid));
        Categories categories = this.categoryRepo.findById(catid).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",catid));
        Post  post = this.modelMapper.map(postDTO,Post.class);
        post.setImagename("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategories(categories);
        Post updatedpost = this.postRepo.save(post);
        return this.modelMapper.map(updatedpost,PostDTO.class);
    }

    @Override
    public PostResponse getAllPagin(Integer pageNumber, Integer pageSize,String sortBy,String sortDirn) {
        // we can use sort method where in sortby we need to pass our variables like sortby id , title etc. which we declare in dtc and in sortdirn we use ascending or descending
        Sort sort = (sortDirn.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending());
        Pageable p = PageRequest.of(pageNumber,pageSize,sort);
        Page<Post>posts = this.postRepo.findAll(p);
        List<Post>allposts = posts.getContent();
        List<PostDTO>postDTOS = allposts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse() ;
        postResponse.setPostDTOList(postDTOS);
        postResponse.setLastPage(posts.isLast());
        postResponse.setPageSize(posts.getSize());
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setTotal_pages(posts.getTotalPages());
        postResponse.setTotal_elements(posts.getTotalElements());
        return postResponse;
    }

    @Override
    public PostDTO UpdatePost(PostDTO postDTO, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id",postId));
        post.setContent(postDTO.getContent());
        post.setTitle(postDTO.getTitle());
        post.setImagename(postDTO.getImagename());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost,PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post delpost = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        this.postRepo.delete(delpost);
    }

    @Override
    public List<PostDTO> getAll() {
        List<Post>posts = this.postRepo.findAll();
        return (posts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        return this.modelMapper.map(post,PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllByCategory(Integer catId) {
        Categories categories = this.categoryRepo.findById(catId).orElseThrow(()->new ResourceNotFoundException("Categories","CategoryId",catId));
        List<Post>posts = this.postRepo.findByCategories(categories);
        List<PostDTO>postDTOS=posts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> getAllByUser(Integer uid) {
        User user = this.userepo.findById(uid).orElseThrow(()->new ResourceNotFoundException("User","userid",uid));
        List<Post>posts = this.postRepo.findByUser(user);
        return (posts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList()));
    }

    @Override
    public List<PostDTO> searchPosts(String Keyword) {
        List<Post>posts = this.postRepo.findByTitleContaining(Keyword);
        List<PostDTO>postDTOS = posts.stream().map((post)->this.modelMapper.map(post,PostDTO.class)).collect(Collectors.toList());
        return postDTOS;
    }
}
