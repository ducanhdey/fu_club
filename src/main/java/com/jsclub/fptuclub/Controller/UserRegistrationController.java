package com.jsclub.fptuclub.Controller;


import com.jsclub.fptuclub.Entity.User;
import com.jsclub.fptuclub.Entity.UserDto;
import com.jsclub.fptuclub.Service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserRegistrationController {

	private UserServiceImpl userService;

	public UserRegistrationController(UserServiceImpl userService) {
		super();
		this.userService = userService;
	}

	@RequestMapping("/registration")
	public String showRegistrationForm(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}


	@PostMapping("/register")
	public String registerUserAccount(@ModelAttribute("user") UserDto user,
									  Model model) {
		User existingUser = userService.findByUserName(user.getUserName());
		if(existingUser!=null){
			model.addAttribute("user", user);
			return "/registration";
		}
		userService.save(user);
		return "redirect:/registration?success";
	}
}