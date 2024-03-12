package com.jsclub.fptuclub.Model.Service;

import com.jsclub.fptuclub.Model.Entity.Users;

public interface UserService {
	public Users findByUsername(String userName);
	boolean existsByUsername(String userName);
	boolean existsByEmail(String email);
	Users saveOrUpdate(Users users);
}
