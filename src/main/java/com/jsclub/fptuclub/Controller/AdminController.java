package com.jsclub.fptuclub.Controller;

import com.jsclub.fptuclub.Model.Entity.Post;
import com.jsclub.fptuclub.Model.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	PostRepository postRepository;
	@PostMapping("/createpost")
	public String createPost(@ModelAttribute("post") Post post){
		postRepository.save(post);
		return "AdminPage";
	}
	@PostMapping("/delete/{id}")
	public String deletePost(@PathVariable("id") Integer id){
		postRepository.deleteById(id);
		return "AdminPage";
	}


}
