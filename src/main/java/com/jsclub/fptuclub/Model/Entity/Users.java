package com.jsclub.fptuclub.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserID")
	private int userID;
	@Column(name = "UserName",unique = true,nullable = false)
	private String username;
	@Column(name = "Password",nullable = false)
	private String password;
	@Column(name = "Created")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date created;
	@Column(name = "User Status")
	private boolean UserStatus;
	@Column(name = "email")
	private String email;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Users_roles",
			joinColumns = @JoinColumn(name = "users_UserID"),
			inverseJoinColumns = @JoinColumn(name = "roles_RoleID"))
	private Set<Role> roles = new LinkedHashSet<>();

}
