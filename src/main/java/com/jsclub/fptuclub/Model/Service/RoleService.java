package com.jsclub.fptuclub.Model.Service;

import com.jsclub.fptuclub.Model.Entity.ERole;
import com.jsclub.fptuclub.Model.Entity.Role;

import java.util.Optional;

public interface RoleService {
	Optional<Role> findByRoleName(ERole roleName);

}
