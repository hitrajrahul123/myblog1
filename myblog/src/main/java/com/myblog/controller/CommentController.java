package com.myblog.controller;

import com.myblog.Service.CommentService;
import com.myblog.entity.Comment;
import com.myblog.payload.CommentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private CommentService commentService;

  public CommentController(CommentService commentService){

      this.commentService=commentService;
    }

    //http://localhost:8383/api/posts/1/comment
    @PostMapping ("/posts/{postId}/comment")
  public ResponseEntity <Object>createComment(@Valid @PathVariable("postId")long postId, @RequestBody CommentDto commentDto, BindingResult result){

      if (result.hasErrors()){
          return new ResponseEntity<Object>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
      }
        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity(dto, HttpStatus.CREATED);

    }

    //http://localhost:8383/api/posts/1/comment
    @GetMapping("/posts/{postId}/comment")
    public List<CommentDto>getCommentByPostId(@PathVariable("postId")long postId){
        List<CommentDto> id = commentService.getCommentByPostId(postId);
        return id;
    }

    //http://localhost:8383/api/posts/1/comment/1
    @GetMapping("/posts/{postId}/comment/{id}")
   public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId")long postId,@PathVariable("id")long commentId){
        CommentDto dto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    //Developing Update Comment Rest api
    //http://localhost:8383/api/posts/1/comment/1
    @PutMapping("/posts/{postId}/comment/{id}")
   public ResponseEntity<CommentDto> updateComment(@PathVariable("postId")long postId,@PathVariable("id")long  id,@RequestBody CommentDto commentDto){
      CommentDto dto = commentService.updateComment(postId,id,commentDto);
   return new ResponseEntity<>(dto,HttpStatus.OK);
  }

  // Update Comment rest api

    @DeleteMapping("/posts/{postId}/comment/{id}")
   public ResponseEntity<String> deleteComment(@PathVariable("postId")long postId,@PathVariable("id")long id){
     commentService.deleteComment(postId,id);
        return new ResponseEntity<>("Comment is deleted",HttpStatus.OK);
    }
}
