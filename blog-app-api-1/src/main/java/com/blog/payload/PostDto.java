package com.blog.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blog.entity.Comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostDto {
	
	
    private Integer postId;
	
	@NotBlank
	@Size(min=4, max=150, message="Range of post title is 4-150 charactrs")
	private String title;
	
	@NotBlank
	@Size(min=4, max=150, message="Range of category title is 4-150 charactrs")
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private UserDto user;
	
	private CategoryDto category;
	
	private Set<CommentDto> comment=new HashSet<>();

}
