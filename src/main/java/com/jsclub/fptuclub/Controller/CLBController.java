package com.jsclub.fptuclub.Controller;

import com.jsclub.fptuclub.Model.Entity.CLB;
import com.jsclub.fptuclub.Model.Repository.CLBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CLBController {
	@Autowired
	CLBRepository clbRepository;

	@GetMapping("/view/{id}")
	public String viewClub(@PathVariable("id") Integer id, Model model) {
		CLB clb = clbRepository.findByCID(id);
		model.addAttribute("background", clb.getBackground());
		model.addAttribute("fullname", clb.getFullName());
		model.addAttribute("shortname", clb.getShortName());
		model.addAttribute("mail", clb.getMail());
		model.addAttribute("date", clb.getDate());
		model.addAttribute("logo", clb.getLogo());
		model.addAttribute("intro", clb.getIntro());
		model.addAttribute("facebook", clb.getFb());
//		model.addAttribute("contentType", "application/json");

		return "viewClub";
	}
}
