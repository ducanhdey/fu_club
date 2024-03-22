package com.jsclub.fptuclub.Controller;

import com.jsclub.fptuclub.Model.Entity.CLB;
import com.jsclub.fptuclub.Model.Entity.Post;
import com.jsclub.fptuclub.Model.Repository.CLBRepository;
import com.jsclub.fptuclub.Model.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.jsclub.fptuclub.Controller.UserController.userlogin;
import java.util.List;
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	CLBRepository clbRepository;
	@Autowired
	PostRepository postRepository;
	@GetMapping("/adminpage")
	public String adminPage(Model model) {
		model.addAttribute("contentType", "application/json");
		if (userlogin.getRole().getRoleName().equals("ADMIN")) {
			Post uppost = new Post();
			CLB clb = clbRepository.findByCID(userlogin.getManage_clb().getCID());
			model.addAttribute("club", clb);
			model.addAttribute("uppost", uppost);
			List<Post> listPost = (List<Post>) postRepository.findAllByClubID(userlogin.getManage_clb());
			if (listPost != null) {
				model.addAttribute("listpost", listPost);
			}
			return "AdminPage";
		}
		return "redirect:/home";
	}
	@PostMapping("/createpost")
	public String createPost(@ModelAttribute("post") Post post){
		post.setClubID(userlogin.getManage_clb());
		postRepository.save(post);
		return "redirect:/admin/adminpage";
	}
	@PostMapping("/delete/{id}")
	public String deletePost(@PathVariable("id") Integer id){
		postRepository.deleteById(id);
		return "redirect:/admin/adminpage";
	}


}
