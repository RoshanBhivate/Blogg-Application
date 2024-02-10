package com.blog.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Builder
public class CategoryDto {
	
	
	
	
	private Integer categoryId;
	
	@NotBlank
	@Size(min=4, max=12, message="Range of category title is 4-12 charactrs")
	private String categoryTitle;
	
	@NotBlank
	@Size(min=4, max=50, message="Range of category title is 4-50 charactrs")
	private String categoryDescription;
	

}
