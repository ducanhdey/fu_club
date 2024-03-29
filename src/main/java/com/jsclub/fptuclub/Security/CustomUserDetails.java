package com.jsclub.fptuclub.Security;

import com.jsclub.fptuclub.Model.Entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
	private int userId;
	private String userName;
	@JsonIgnore
	private String password;
	private String email;
	private boolean userStatus;
	private String fullName;
	private String studentId;
	private String gender;
	// Quyen cua user
	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	// tu chuyen thong tin tu uuser sang userdetail
	public static CustomUserDetails mapUserToUserDetail(Users user) {
		// lay cac quyen tu user
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRoleName());
		List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
		listAuthorities.add(authority);
		// tra ve doi tuong customuserdetail
		return new CustomUserDetails(user.getUserID(),
				user.getUsername(),
				user.getPassword(),
				user.getEmail(),
				user.isUserStatus(),
				user.getFullName(),
				user.getStudentId(),
				user.getGender(),
				listAuthorities);
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
