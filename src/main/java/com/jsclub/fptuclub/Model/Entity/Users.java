package com.jsclub.fptuclub.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinTable(name = "member",
			joinColumns = @JoinColumn(name = "users_UserID"),
			inverseJoinColumns = @JoinColumn(name = "CLBs_cid"))
	private Set<CLB> clb = new LinkedHashSet<>();

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinTable(name = "Request",
			joinColumns = @JoinColumn(name = "users_UserID"),
			inverseJoinColumns = @JoinColumn(name = "clb_cid"))
	private Set<CLB> clbs = new LinkedHashSet<>();

	@ManyToOne
	@JoinColumn(name = "role_role_id")
	private Role role;

}
