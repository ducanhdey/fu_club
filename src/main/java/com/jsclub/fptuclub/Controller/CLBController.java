package com.jsclub.fptuclub.Controller;

import com.jsclub.fptuclub.Model.Entity.CLB;
import com.jsclub.fptuclub.Model.Entity.Post;
import com.jsclub.fptuclub.Model.Repository.CLBRepository;
import com.jsclub.fptuclub.Model.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import static com.jsclub.fptuclub.Controller.UserController.userlogin;

@Controller
public class CLBController {
	@Autowired
	CLBRepository clbRepository;
	@Autowired
	PostRepository postRepository;

	@GetMapping("/view/{id}")
	public String viewClub(@PathVariable("id") Integer id, Model model) {
		CLB clb = clbRepository.findByCID(id);
		model.addAttribute("club", clb);

		List<Post> listPost = postRepository.findAllByClubID(clb);
		if (listPost != null) {
			model.addAttribute("listpost", listPost);
		}
		return "viewclub";
	}
}

