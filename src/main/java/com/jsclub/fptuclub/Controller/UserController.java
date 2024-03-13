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
	public String homePage() {
		return "index";
	}

	@GetMapping("/login")
	public String loginPage(Model model) {
		LoginRequest loginRequest = new LoginRequest();
		model.addAttribute("loginRequest",loginRequest);
		return "login";

	}

	@PostMapping("/signin")
	public String loginUser(@ModelAttribute("loginRequest") LoginRequest loginRequest) {
//		System.out.println("aaaaaa");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
		);
//		System.out.println("bbbbbbb");
		SecurityContextHolder.getContext().setAuthentication(authentication);
//		System.out.println("ccccc");
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		//Sinh jwt tra ve client
		String jwt = jwtTokenProvider.generateToken(customUserDetails);
		//lay cac quyen cua user
//		System.out.println(jwt);
//		List<String> roles = customUserDetails.getAuthorities().stream()
//				.map(item->item.getAuthority()).collect(Collectors.toList());

		return "redirect:/";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		SignupRequest signupRequest = new SignupRequest();
		model.addAttribute("signupRequest",signupRequest);
		return "registration";
	}

	@PostMapping("/signup")
	public String registerUser(@ModelAttribute("signupRequest") SignupRequest signupRequest, Model model) {
		if (userService.existsByUsername(signupRequest.getUsername())) {
//			boolean checkExistUser = true;
			return "redirect:/registration";
		}
		if (userService.existsByEmail(signupRequest.getEmail())) {
//			boolean checkExistEmail = true;
			return "redirect:/registration";
		}
		System.out.println("aaaaa");
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
		user.setUserStatus(true);
		user.setRole(new Role("USER"));


		userService.saveOrUpdate(user);
		return "redirect:/registration?success";
	}

}
