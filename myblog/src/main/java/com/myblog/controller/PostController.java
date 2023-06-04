package com.myblog.controller;

import com.myblog.Service.PostService;
import com.myblog.entity.Post;
import com.myblog.payload.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;
    public PostController(PostService postService){
        this.postService = postService;
    }

    //get all post rest api
    //http://localhost:8383/api/posts

    @PreAuthorize("hasRole('Admin')")
    @PostMapping
    public ResponseEntity<Object>createPost(@Valid @RequestBody PostDto postDto, BindingResult result){

        if(result.hasErrors()) {
        return  new ResponseEntity<Object>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);


    }
    //http://localhost:8383/api/posts
    @GetMapping
    public List<PostDto> getAllPosts(){
         return postService.getAllPosts();

    }
    //get by id

    //http://localhost:8383/api/posts/1
    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id")long id){
        PostDto dto = postService.getPostById(id);
        return ResponseEntity.ok(dto);
    }

    //update post by id rest api

    //http://localhost:8383/api/posts/1

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable(name="id")long id){
        PostDto dto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


    //delete post rest api
    //http://localhost:8383/api/posts/1

    @PreAuthorize("hasRole('Admin')")
@DeleteMapping("/{id}")
  public ResponseEntity<String> deletePost(@PathVariable(name = "id")long id){
      postService.deletePostById(id);
      return new ResponseEntity<>("Post entity deleted successfully",HttpStatus.OK);
    }
}
