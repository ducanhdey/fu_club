package com.jsclub.fptuclub.Model.ServiceImpl;

import com.jsclub.fptuclub.Model.Entity.Users;
import com.jsclub.fptuclub.Model.Repository.UserRepository;
import com.jsclub.fptuclub.Model.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Override
	public Users findByUsername(String userName) {
		return userRepository.findByUsername(userName);
	}

	@Override
	public boolean existsByUsername(String userName) {
		return userRepository.existsByUsername(userName);
	}

	@Override
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public Users saveOrUpdate(Users users) {
		return userRepository.save(users);
	}


}
