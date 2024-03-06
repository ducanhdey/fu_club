package com.jsclub.fptuclub.Entity;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link User}
 */
@Value
public class UserDto implements Serializable {
	Integer studentId;
	String fullName;
	String gender;
	String email;
	String userName;
	String password;
}