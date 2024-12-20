package isika.p3.amappli.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.dto.NewTenancyDTO;
import isika.p3.amappli.dto.NewUserDTO;
import isika.p3.amappli.dto.ValueDTO;
import isika.p3.amappli.entities.tenancy.ColorPalette;
import isika.p3.amappli.entities.tenancy.ContentBlock;
import isika.p3.amappli.entities.tenancy.FontChoice;
import isika.p3.amappli.entities.user.User;
import isika.p3.amappli.exceptions.EmailAlreadyExistsException;
import isika.p3.amappli.service.ContentBlockService;
import isika.p3.amappli.service.TenancyService;
import isika.p3.amappli.service.UserService;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/start/")
public class LoginController {

    private final UserService userService;
    private final TenancyService tenancyService;
    private final ContentBlockService contentBlockService;

    public LoginController(UserService userService, TenancyService tenancyService, ContentBlockService contentBlockService) {
        this.userService = userService;
        this.tenancyService = tenancyService;
        this.contentBlockService = contentBlockService;
    }

    @GetMapping("/signup")    
    public String plateformUserSignUpForm(Model model){
        NewUserDTO newUserDTO = new NewUserDTO();
        model.addAttribute("newUserDTO",newUserDTO);
        return "amappli/platformlogin/signup";
    }

    @PostMapping("/signup")
    public String plateformUserSignup(@Valid @ModelAttribute NewUserDTO newUserDTO, BindingResult result, Model model){
        if(result.hasErrors()){
            return "amappli/platformlogin/signup";
        }
        // Get info from DTO into new User
        User user = new User();
        BeanUtils.copyProperties(newUserDTO,user);
        // Nested properties are not copied by BeanUtils
        user.setAddress(newUserDTO.getAddress());
        user.setContactInfo(newUserDTO.getContactInfo());

        // Write user in DB
        try {
            userService.addPlatformUser(user);
        }
        catch (EmailAlreadyExistsException e)
        {
            model.addAttribute("emailError", e.getMessage());
            return "amappli/platformlogin/signup";
        }
        return "amappli/platformlogin/signupdone";
    }

    @GetMapping("/creation")
    public String createTenancy(Model model) {
        NewTenancyDTO newTenancyDTO = new NewTenancyDTO();
        // Three possibles values
        List<ValueDTO> values = new ArrayList<ValueDTO>();
        values.add(new ValueDTO());
        values.add(new ValueDTO());
        values.add(new ValueDTO());
        newTenancyDTO.setValues(values);
        model.addAttribute("newTenancyDTO",newTenancyDTO);
        // Give the color palettes options for the form
        model.addAttribute("colorPalettes",ColorPalette.values());
        model.addAttribute("fontChoices", FontChoice.values());
        return "amappli/platformlogin/createtenancy";
    }

    @PostMapping("/creation")
    public String tenancyCreation(@ModelAttribute("newTenancyDTO") NewTenancyDTO newTenancyDTO) {

        tenancyService.createTenancyFromWelcomeForm(newTenancyDTO);
        return "amappli/platformlogin/signupdone";
    }

    @GetMapping("/showimg")
    public String tryToShowImg(Model model){
        ContentBlock cb = contentBlockService.findById(1L);
        String base64Image = "data:"+cb.getContentImgTypeMIME()+";base64," + cb.getContentImg();
        model.addAttribute("image", base64Image);
        return "amappli/platformlogin/showimg";
    }
    
}
