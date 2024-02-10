package com.blog.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PostResponse {
	
	private List<PostDto> content;
	private Integer pageNumber;
	private Integer pageSize;
	private Long totalElements;
	private Integer totalPages;
	private boolean lastPage;
	
	
	
}
