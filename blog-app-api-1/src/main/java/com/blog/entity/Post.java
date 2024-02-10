package com.blog.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="post")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	@Column(name="post_title", length=100, nullable = false)
	private String title;
	
	
	
	@Column(length=1000)
	private String content;
	
	
	private String imageName;
	
	 @Temporal(TemporalType.TIMESTAMP)
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	 private Date addedDate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL)
	private Set<Comment> comment=new HashSet<>();

	

}
