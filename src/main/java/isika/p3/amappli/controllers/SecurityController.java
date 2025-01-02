package isika.p3.amappli.controllers;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sectest")
public class SecurityController {
    
    // private final PermissionService permissionService;


    // public SecurityController(PermissionService permissionService) {
    //     this.permissionService = permissionService;
    // }

    @GetMapping("/login")
    public String goToLogin(Model model){
        // permissionService.createPermissions();
        return "secexamples/login";
    }

    @GetMapping("/logout")
    public String goToLogout(Model model){
        return "secexamples/logout";
    }

    @GetMapping("/needanyauth")
    public String goToAuthTest(Model model){
        return "secexamples/needanyauth";
    }

    @GetMapping("/showpermissions")
    public String showLoggedUserPermissions(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal().toString());
        model.addAttribute("principal", authentication.getPrincipal());
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        model.addAttribute("authorities", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
       return "secexamples/showloggeduserpermissions"; 
    }

    @PreAuthorize("hasAuthority('Permission 1')")
    @GetMapping("/needpermission1")
    public String needPermissionA(){
        return "secexamples/needpermission1";
    }

    @PreAuthorize("hasAuthority('Permission 2')")
    @GetMapping("/needpermission2")
    public String needPermissionB(){
        return "secexamples/needpermission2";
    }
}
