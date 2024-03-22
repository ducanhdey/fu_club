package com.jsclub.fptuclub.Payload.Request;

import com.jsclub.fptuclub.Model.Entity.Role;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Set;

public class SignupRequest {
	public SignupRequest() {
	}

	public SignupRequest(String username, String password, String email, Date created, boolean userStatus, Role role,
			String fullName, String studentId, String gender) {
		this.username = username;
		this.password = password;
		this.email = email;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date now = new Date();
		String dateNow = sdf.format(now);
		try {
			this.created = sdf.parse(dateNow);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.userStatus = true;
		this.role = role;
		this.fullName = fullName;
		this.studentId = studentId;
		this.gender = gender;
	}

	private String username;
	private String password;
	private String email;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created;
	private boolean userStatus;
	private Role role;
	private String fullName;
	private String studentId;
	private String gender;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public boolean isUserStatus() {
		return userStatus;
	}

	public void setUserStatus(boolean userStatus) {
		this.userStatus = userStatus;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
