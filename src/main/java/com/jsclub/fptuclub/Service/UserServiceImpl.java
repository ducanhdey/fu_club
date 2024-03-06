package com.jsclub.fptuclub.Service;

import com.jsclub.fptuclub.Entity.Role;
import com.jsclub.fptuclub.Entity.User;
import com.jsclub.fptuclub.Entity.UserDto;
import com.jsclub.fptuclub.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepo;
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public User save(UserDto userDto) {
		User user = new User(
				userDto.getStudentId(),
				userDto.getFullName(),
				userDto.getGender(),
				userDto.getEmail(),
				userDto.getUserName().toUpperCase(),
				passwordEncoder.encode(userDto.getPassword()),
				new Role("USER"));
		return userRepo.save(user);
	}

	@Override
	public User findByUserName(String username) {
		User user = userRepo.findByUserName(username);
		return null;
	}
}
