package com.jsclub.fptuclub.Model.Repository;

import com.jsclub.fptuclub.Model.Entity.CLB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CLBRepository extends JpaRepository<CLB,Integer> {
 	CLB findByFullName(String FullName);
	CLB findByShortName(String ShortName);
	CLB findByCID(Integer CID);
}
