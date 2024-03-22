package com.jsclub.fptuclub.Controller;

import com.jsclub.fptuclub.Model.Entity.CLB;
import com.jsclub.fptuclub.Model.Entity.Users;
import com.jsclub.fptuclub.Model.Repository.CLBRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.jsclub.fptuclub.Model.Repository.UserRepository;
import com.jsclub.fptuclub.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	private CLBRepository repository;
	@Autowired
	private UserRepository usersRepository;

	@GetMapping("/searchclb")
	public ModelAndView searchCLB(@RequestParam("keyword") String searchName){
		ModelAndView mav = new ModelAndView("searchPage");
		List<CLB> listCLB = repository.findAll();
		List<CLB> foundCLB = new ArrayList<>();
		for(CLB clb : listCLB){
			if(clb.getFullName().toLowerCase().contains(searchName.toLowerCase()) ||
					clb.getShortName().toLowerCase().contains(searchName)){
				foundCLB.add(clb);
			}
		}

		mav.addObject("listfoundclb",foundCLB);
		return mav;
	}
	@GetMapping("/logout")
	public String getLogout(){
		return "redirect:/login";
	}


}
