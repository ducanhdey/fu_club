package com.jsclub.fptuclub.Model.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_role_id")
	private Role role;

	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinTable(name = "Users_clbS",
			joinColumns = @JoinColumn(name = "users_UserID"),
			inverseJoinColumns = @JoinColumn(name = "clb_CID"))
	private List<CLB> clubS = new ArrayList<>();

}
