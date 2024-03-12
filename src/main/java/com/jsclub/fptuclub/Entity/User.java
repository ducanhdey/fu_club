package com.jsclub.fptuclub.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "id", nullable = false)
	private Integer studentId;
	private String fullName;
	private String gender;
	@Column(unique = true)
	private String email;
	@Column(unique = true)
	private String userName;
	private String password;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	private Role role;

}