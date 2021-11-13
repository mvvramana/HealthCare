package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.entity.Specialization;
import com.app.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	@Autowired
	private ISpecializationService service;

	/**
	 * 1.show register page
	 */
	@GetMapping("/register")
	public String displayRegister() {
		return "SpecializationRegister";
	}

	/**
	 * 2.on submit form save data
	 */
	@PostMapping("/create")
	public String saveForm(@ModelAttribute Specialization specialization, Model model) {
		Long id = service.saveSpecialization(specialization);
		String message = "Record " + id + " is created successfully!";
		model.addAttribute("message", message);
		return "SpecializationRegister";
	}

	/**
	 * 3.display all specialization
	 */
	@GetMapping("/all")
	public String viewAll(Model model, @RequestParam(value = "message", required = false) String message) {
		List<Specialization> list = service.getAllSpecialization();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "SpecializationData";
	}

	/**
	 * 4.delete data based on id
	 */
	@GetMapping("/delete")
	public String removeData(@RequestParam Long id, RedirectAttributes attributes) {
		service.removeSpecialization(id);
		String message = "Record " + id + " is deleted successfully!";
		attributes.addAttribute("message", message);
		return "redirect:all";
	}

}
