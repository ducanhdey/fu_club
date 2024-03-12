package com.jsclub.fptuclub.Service;


import com.jsclub.fptuclub.Entity.User;

public interface UserService   {
	User findByUserName(String username);
}
