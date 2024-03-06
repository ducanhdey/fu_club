package com.jsclub.fptuclub.Controller;

import com.jsclub.fptuclub.Entity.Person;
import com.jsclub.fptuclub.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PersonController {
	@Autowired
	private PersonService personService;
	@GetMapping("/findAll")
	public String findAll(Model model){
		List<Person> list = personService.findAll();33
		model.addAttribute("listperson", list);
		return "index";
	}
	@PostMapping("/")
}
