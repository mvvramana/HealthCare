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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.entity.Specialization;
import com.app.exception.SpecializationNotFoundException;
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
		try {

			service.removeSpecialization(id);
			String message = "Record " + id + " is deleted successfully!";
			attributes.addAttribute("message", message);
		} catch (SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:all";
	}

	/**
	 * 5.fetch data to edit operation
	 */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id, Model model, RedirectAttributes attributes) {
		String page = null;
		try {

			Specialization spec = service.getOneSpecializatio(id);
			model.addAttribute("specialization", spec);
			page = "SpecializationEdit";
		} catch (SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		return page;
	}

	/**
	 * 6.update data based on id
	 */
	@PostMapping("/update")
	public String updateData(@ModelAttribute Specialization specialization, RedirectAttributes attributes) {
		service.updateSpecialization(specialization);
		String message = "Record " + specialization.getId() + " is updated successfully!";
		attributes.addAttribute("message", message);
		return "redirect:all";
	}

	/**
	 * 7.To check dublicate code is exist or not using ajax call
	 */

	@GetMapping("/checkCode")
	@ResponseBody
	public String validateSpecCode(@RequestParam String code, @RequestParam Long id) {
		String message = "";
		if (id == 0 && service.isSpecCodeExist(code)) {
			message = code + " is already exist!";
		} else if (id != 0 && service.isSpecCodeExistForEdit(code, id)) {
			message = code + " is already exist!";
		}
		return message;
	}

	/**
	 * 8. To check dublicate name is exist or not using ajax call
	 */
	@GetMapping("checkName")
	@ResponseBody
	public String validateSpecName(@RequestParam String name, @RequestParam Long id) {
		String message = "";
		if (id == 0 && service.isSpecNameExist(name)) {
			message = name + " is already exist!";
		} else if (id != 0 && service.isSpecNameExistForEdit(name, id)) {
			message = name + " is already exist!";
		}
		return message;
	}

}
