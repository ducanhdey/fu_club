package com.jsclub.fptuclub.Controller;

import com.jsclub.fptuclub.JWT.JwtTokenProvider;
import com.jsclub.fptuclub.Model.Entity.ERole;
import com.jsclub.fptuclub.Model.Entity.Role;
import com.jsclub.fptuclub.Model.Entity.Users;
import com.jsclub.fptuclub.Model.Service.RoleService;
import com.jsclub.fptuclub.Model.Service.UserService;
import com.jsclub.fptuclub.Payload.Request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleService roleService;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@GetMapping({"/home","/"})
	public String homePage() {
		return "index";
	}

	@GetMapping("/login")
	public String loginPage() {

		return "login";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		SignupRequest signupRequest = new SignupRequest();
		model.addAttribute("signuprequest", signupRequest);
		return "registration";
	}

	@PostMapping("/signup")
	public String registerUser(@ModelAttribute("signuprequest") SignupRequest signupRequest, Model model) {
		if (userService.existsByUsername(signupRequest.getUsername())) {
			boolean checkExistUser = true;
			model.addAttribute("checkExistUser", checkExistUser);
			return "redirect:/registration";
		}
		if (userService.existsByEmail(signupRequest.getEmail())) {
			boolean checkExistEmail = true;
			model.addAttribute("checkExistEmail", checkExistEmail);
			return "redirect:/registration";
		}
		Users user = new Users();
		user.setUsername(signupRequest.getUsername());
		user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		user.setEmail(signupRequest.getEmail());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date now = new Date();
		String dateNow = sdf.format(now);
		try{
			user.setCreated(sdf.parse(dateNow));
		}catch (Exception e){
			e.printStackTrace();
		}
		user.setUserStatus(true);
		Set<String> strRoles = signupRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			//User quyen mac dinh
			Role userRole = roleService.findByRoleName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException());
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleService.findByRoleName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Role is not found"));
						roles.add(adminRole);
					case "user":
						Role userRole = roleService.findByRoleName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Role is not found"));
						roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		userService.saveOrUpdate(user);
		return "redirect:/registration?success";
	}

}
