package isika.p3.amappli.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.service.UserService;

@Controller
@RequestMapping("/")
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")    
    public String plateformUserSignUpForm(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "platformlogin/signup";
    }

    @PostMapping("/signup")
    public String plateformUserSignup(@ModelAttribute User user, Model model){
        userService.addPlatformUser(user);
        return "platformlogin/signupdone";
    }

}
