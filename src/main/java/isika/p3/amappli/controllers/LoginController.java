package isika.p3.amappli.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import isika.p3.amappli.dto.NewUserDTO;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.exceptions.EmailAlreadyExistsException;
import isika.p3.amappli.service.UserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/login/")
public class LoginController {

	private final UserService userService;

	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/signup")
	public String plateformUserSignUpForm(Model model) {
		NewUserDTO newUserDTO = new NewUserDTO();
		model.addAttribute("newUserDTO", newUserDTO);
		return "platformlogin/signup";
	}

	@PostMapping("/signup")
	public String plateformUserSignup(@Valid @ModelAttribute NewUserDTO newUserDTO, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "platformlogin/signup";
		}
		// Get info from DTO into new User
		User user = new User();
		BeanUtils.copyProperties(newUserDTO, user);
		// Nested properties are not copied by BeanUtils
		user.setAddress(newUserDTO.getAddress());
		user.setContactInfo(newUserDTO.getContactInfo());

		// Write user in DB
		try {
			userService.addPlatformUser(user);
		} catch (EmailAlreadyExistsException e) {
			model.addAttribute("emailError", e.getMessage());
			return "platformlogin/signup";
		}
		return "platformlogin/signupdone";
	}


}
