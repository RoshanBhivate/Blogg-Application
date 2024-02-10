package com.blog.payload;




import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
public class UserDto {
	
	
	
	private Integer id;
	
	@NotEmpty
	@Size(min=4,message = "Username must be greater that 4 words")
	private String name;
	
	@Email(message = "Email address is not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min = 3,max = 8,message = "password must be in range of 3 to 8 char")
	private String password;
	
	@NotEmpty
	private String about;
	
	
	
}
