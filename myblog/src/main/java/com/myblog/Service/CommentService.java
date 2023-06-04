package com.myblog.Service;

import com.myblog.entity.Comment;
import com.myblog.payload.CommentDto;

import java.awt.*;
import java.util.List;

public interface CommentService {
  public CommentDto createComment(long postId,CommentDto commentDto);

 List<CommentDto> getCommentByPostId(long postId);

CommentDto getCommentById(Long postId, Long commentId);


    CommentDto updateComment(long postId,long id, CommentDto commentDto);

    void deleteComment(long postId, long id);
}
