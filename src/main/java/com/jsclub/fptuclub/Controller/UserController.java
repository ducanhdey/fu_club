package com.jsclub.fptuclub.Controller;

import com.jsclub.fptuclub.JWT.JwtTokenProvider;
import com.jsclub.fptuclub.Model.Entity.Role;
import com.jsclub.fptuclub.Model.Entity.Users;
import com.jsclub.fptuclub.Model.Service.RoleService;
import com.jsclub.fptuclub.Model.Service.UserService;
import com.jsclub.fptuclub.Payload.Request.LoginRequest;
import com.jsclub.fptuclub.Payload.Request.SignupRequest;
import com.jsclub.fptuclub.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.*;

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
	@Autowired
	private AuthenticationManager authenticationManager;


	@GetMapping({"/home", "/"})
	public String homePage(Model model) {
		model.addAttribute("contentType", "application/json");
		return "index";
	}

	@GetMapping("/login")
	public String loginPage(Model model) {
		LoginRequest loginRequest = new LoginRequest();
		model.addAttribute("loginRequest",loginRequest);
		model.addAttribute("contentType", "application/json");
		return "login";

	}

	@PostMapping("/signin")
	public String loginUser(@ModelAttribute("loginRequest") LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		//Sinh jwt tra ve client
		String jwt = jwtTokenProvider.generateToken(customUserDetails);

		return "redirect:/home";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		SignupRequest signupRequest = new SignupRequest();
		model.addAttribute("signupRequest",signupRequest);
		model.addAttribute("contentType", "application/json");
		return "registration";
	}
	@PostMapping("/signup")
<<<<<<< HEAD
	public String registerUser(@ModelAttribute("signupRequest") SignupRequest signupRequest) {
		if (userService.existsByUsername(signupRequest.getUsername())) {
//			boolean checkExistUser = true;
			return "redirect:/registration?existusername";
		}
		if (userService.existsByEmail(signupRequest.getEmail())) {
//			boolean checkExistEmail = true;
			return "redirect:/registration?existemail";
		}
=======
	public String registerUser(@ModelAttribute("signupRequest") SignupRequest signupRequest, Model model) {
//		if (userService.existsByUsername(signupRequest.getUsername())) {
////			boolean checkExistUser = true;
//			return "redirect:/registration";
//		}
//		if (userService.existsByEmail(signupRequest.getEmail())) {
////			boolean checkExistEmail = true;
//			return "redirect:/registration";
//		}
>>>>>>> 6e5a41b4f13cd51b210201f3903b2a3803f26b2d
		Users user = new Users();
		user.setUsername(signupRequest.getUsername());
		user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
		user.setEmail(signupRequest.getEmail());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date now = new Date();
		String dateNow = sdf.format(now);
		try {
			user.setCreated(sdf.parse(dateNow));
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setFullName(signupRequest.getFullName());
		user.setStudentid(signupRequest.getStudentid());
		user.setGender(signupRequest.getGender());
		user.setUserStatus(true);
<<<<<<< HEAD
		user.setRole(new Role("USER"));

=======
		user.setRole(new Role("ADMIN"));
//		if (strRoles == null) {
//			//User quyen mac dinh
//			Role userRole = roleService.findByRoleName(ERole.ROLE_USER).get();
//			roles.add(userRole);
//		} else {
//			strRoles.forEach(role -> {
//				switch (role) {
//					case "admin":
//						Role adminRole = roleService.findByRoleName(ERole.ROLE_ADMIN)
//								.orElseThrow(() -> new RuntimeException("Role is not found"));
//						roles.add(adminRole);
//					case "user":
//						Role userRole = roleService.findByRoleName(ERole.ROLE_USER)
//								.orElseThrow(() -> new RuntimeException("Role is not found"));
//						roles.add(userRole);
//				}
//			});
//		}
>>>>>>> 6e5a41b4f13cd51b210201f3903b2a3803f26b2d

		userService.saveOrUpdate(user);
		return "redirect:/login";
	}

}