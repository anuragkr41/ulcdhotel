package com.ulcd.user.service.UserService.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "micro_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
	@Id
	@Column(name="ID")
	private String userId;
	
	@Column(name="NAME", length = 20)
	private String name;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="About")
	private String about;
	
	@Transient
	private List<Rating> ratings = new ArrayList<>();

}
