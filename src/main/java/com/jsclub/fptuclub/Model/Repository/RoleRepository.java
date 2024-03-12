package com.jsclub.fptuclub.Model.Repository;

import com.jsclub.fptuclub.Model.Entity.ERole;
import com.jsclub.fptuclub.Model.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
	Optional<Role> findByRoleName(ERole roleName);
}
