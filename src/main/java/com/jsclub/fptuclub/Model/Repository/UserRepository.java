package com.jsclub.fptuclub.Model.Repository;

import com.jsclub.fptuclub.Model.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Integer> {
	public Users findByUsername(String userName);
	boolean existsByUsername(String userName);
	boolean existsByEmail(String email);
}
