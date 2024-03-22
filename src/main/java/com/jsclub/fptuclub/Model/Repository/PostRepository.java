package com.jsclub.fptuclub.Model.Repository;

import com.jsclub.fptuclub.Model.Entity.CLB;
import com.jsclub.fptuclub.Model.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

	List<Post> findAllByClubID(CLB clb);


}
