package com.blog.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CommentDto;
import com.blog.repository.CommentRepo;
import com.blog.repository.PostRepo;
import com.blog.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
	private PostRepo postRepo;
    
    @Autowired
    private CommentRepo commentRepo;
    
  // @Autowired
   //private CommentService commentService;
    
    @Autowired
    private ModelMapper modelMapper;
    
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post_id", postId));
	
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		
		comment.setPost(post);
		
		Comment save = this.commentRepo.save(comment);
		

		return this.modelMapper.map(save, CommentDto.class);
		
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","commnetId", commentId));
        this.commentRepo.delete(comment);		
		
		
	}

}
