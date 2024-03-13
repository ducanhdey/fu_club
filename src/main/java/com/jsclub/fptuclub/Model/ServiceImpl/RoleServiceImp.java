package com.jsclub.fptuclub.Model.ServiceImpl;

import com.jsclub.fptuclub.Model.Entity.Role;
import com.jsclub.fptuclub.Model.Repository.RoleRepository;
import com.jsclub.fptuclub.Model.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImp implements RoleService {
	@Autowired
	private RoleRepository roleRepository;
	@Override
	public Optional<Role> findByRoleName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}
}
