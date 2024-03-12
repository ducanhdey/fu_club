package com.jsclub.fptuclub.Model.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Role")
@Data
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RoleID")
	private int roleID;
	@Column(name = "Role name")
	@Enumerated(EnumType.STRING)
	private ERole roleName;
}
