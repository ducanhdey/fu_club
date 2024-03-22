package com.jsclub.fptuclub.Controller;

import com.jsclub.fptuclub.JWT.JwtTokenProvider;
import com.jsclub.fptuclub.Model.Entity.CLB;
import com.jsclub.fptuclub.Model.Entity.Role;
import com.jsclub.fptuclub.Model.Entity.Users;
import com.jsclub.fptuclub.Model.Repository.UserRepository;
import com.jsclub.fptuclub.Model.Service.RoleService;
import com.jsclub.fptuclub.Model.Service.UserService;
import com.jsclub.fptuclub.Payload.Request.LoginRequest;
import com.jsclub.fptuclub.Payload.Request.SignupRequest;
import com.jsclub.fptuclub.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	@Autowired
	private UserRepository userRepository;

	protected static Users  userlogin = new Users();

	@GetMapping("/")
	public String startPage(Model model) {
		model.addAttribute("contentType", "application/json");
		return "index";
	}

	@GetMapping("/home")
	public String homePage(Model model) {
		model.addAttribute("contentType", "application/json");
		return "home";
	}
	@PostMapping("/change")
	public String changePass(@RequestParam("newPass") String newPass) {
		Users user = new Users(userlogin.getUserID(),
				userlogin.getUsername(),
				userlogin.getPassword(),
				userlogin.getCreated(),
				true,
				userlogin.getEmail(),
				userlogin.getFullName(),
				userlogin.getStudentId(),
				userlogin.getGender()
				)
				;
		user.setRole(new Role("USER"));
		System.out.println(userlogin.toString());
		user.setPassword(passwordEncoder.encode(newPass));
		userRepository.save(user);
		return "redirect:/home/userpage";

	}
	@GetMapping("/changepass")
	public String changePass(Model model){
		model.addAttribute("user", userlogin);
		return "Changepassword";
	}
	@GetMapping("/login")
	public String loginPage(Model model) {
		LoginRequest loginRequest = new LoginRequest();
		model.addAttribute("loginRequest", loginRequest);
		model.addAttribute("contentType", "application/json");
		return "login";

	}

	@PostMapping("/signin")
	public String loginUser(@ModelAttribute("loginRequest") LoginRequest loginRequest) {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
//		);
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
//		//Sinh jwt tra ve client
//		String jwt = jwtTokenProvider.generateToken(customUserDetails);
//
		if (userService.findByUsername(loginRequest.getUsername()) != null) {
			if (!passwordEncoder.matches(loginRequest.getPassword(), userService.findByUsername(loginRequest.getUsername()).getPassword())) {
				userlogin = userService.findByUsername(loginRequest.getUsername());
				return "redirect:/home";
			}
		}
			return "redirect:/login";


	}

	@GetMapping("/registration")
	public String registration(Model model) {
		SignupRequest signupRequest = new SignupRequest();
		model.addAttribute("signupRequest", signupRequest);
		model.addAttribute("contentType", "application/json");
		return "registration";
	}

	@PostMapping("/signup")

	public String registerUser(@ModelAttribute("signupRequest") SignupRequest signupRequest) {
		if (userService.existsByUsername(signupRequest.getUsername())) {
//			boolean checkExistUser = true;
			return "redirect:/registration?existusername";
		}
		if (userService.existsByEmail(signupRequest.getEmail())) {
//			boolean checkExistEmail = true;
			return "redirect:/registration?existemail";
		}

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
		user.setStudentId(signupRequest.getStudentId());
		user.setGender(signupRequest.getGender());
		user.setUserStatus(true);
		CLB clb = null;
		clb = new CLB(0,"No club","NC","no",now,"logo","intro","fb","list");
		user.setManage_clb(clb);
		user.setRole(new Role("USER"));


		userService.saveOrUpdate(user);
		return "redirect:/login";
	}
	@GetMapping("/home/userpage")
	public String getUserPage(Model model) {
		model.addAttribute("users", userlogin);
		model.addAttribute("contentType", "application/json");

		return "UserPage";
	}



}
