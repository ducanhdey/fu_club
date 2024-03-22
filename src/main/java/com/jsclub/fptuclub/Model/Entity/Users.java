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
	@Column(name = "UserName", unique = true, nullable = false)
	private String username;
	@Column(name = "Password", nullable = false)
	private String password;
	@Column(name = "Created")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date created;
	@Column(name = "User Status")
	private boolean UserStatus;
	@Column(name = "email", unique = true, nullable = false)
	private String email;
	private String fullName;
	@Column(name = "studentID", unique = true, nullable = true)
	private String studentId;
	private String gender;
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.EAGER)
	@JoinTable(name = "member", joinColumns = @JoinColumn(name = "users_UserID"), inverseJoinColumns = @JoinColumn(name = "CLBs_cid"))
	private Set<CLB> clb = new LinkedHashSet<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH },fetch = FetchType.EAGER)
	@JoinTable(name = "Request", joinColumns = @JoinColumn(name = "users_UserID"), inverseJoinColumns = @JoinColumn(name = "clb_cid"))
	private Set<CLB> clbs = new LinkedHashSet<>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_role_id")
	private Role role;

	@OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
	@JoinColumn(name = "manage_clb_cid", nullable = true)
	private CLB manage_clb;

//	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH },fetch = FetchType.EAGER)
//	@JoinTable(name = "Manage", joinColumns = @JoinColumn(name = "users_UserID"), inverseJoinColumns = @JoinColumn(name = "CLBs_cid"))
//	private Set<CLB> manage = new LinkedHashSet<>();


	public Users(int userID, String username, String password, Date created, boolean userStatus, String email, String fullName, String studentId, String gender) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.created = created;
		UserStatus = userStatus;
		this.email = email;
		this.fullName = fullName;
		this.studentId = studentId;
		this.gender = gender;
	}

	public Users(){}
}