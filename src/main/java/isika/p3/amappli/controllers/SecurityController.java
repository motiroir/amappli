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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import isika.p3.amappli.security.CustomUserDetails;
import isika.p3.amappli.service.amap.GraphismService;

@Controller
public class SecurityController {
    
    private final GraphismService graphismService;

    public SecurityController(GraphismService graphismService) {
        this.graphismService = graphismService;
    }


    @GetMapping("/login")
    public String goToLogin(@RequestParam(name = "error", required = false) String error,Model model){
        // permissionService.createPermissions();
        // if(error != null){
            model.addAttribute("error",error);
        // }
        // else {
            // model.addAttribute("error",false);
        // }
        System.out.println("the error"+error);

        return "secexamples/login";
    }

    @GetMapping("/{tenancyAlias}/login")
    public String goToTenancyLogin(@RequestParam(name = "error", required = false) String error, @PathVariable("tenancyAlias") String alias, Model model) {

        // if(error != null){
            model.addAttribute("error",error);
        // }
        // else {
        //     model.addAttribute("error",false);
        // }
        System.out.println("the error"+error);
        model.addAttribute("tenancyAlias", alias);
        // Appropriate graphism for the tenant
        //get map style depending on tenancy
        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
        //get tenancy info for header footer
        model.addAttribute("tenancy", graphismService.getTenancyByAlias(alias));
        //get color palette
        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
        //get font choice
        model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));

        return "secexamples/tenantlogin";
    }

    @GetMapping("/logout")
    public String goToLogout(Model model){
        return "secexamples/logout";
    }

    @GetMapping("/amappli/needanyauth")
    public String goToAuthTest(Model model){
        return "secexamples/needanyauth";
    }

    @GetMapping("/{tenancyAlias}/needanyauth")
    public String goToAuthTestTenancy(@PathVariable("tenancyAlias") String alias, Model model){
        return "secexamples/needanyauth";
    }

    @GetMapping("/showpermissions")
    public String showLoggedUserPermissions(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails loggedUserInfo = (CustomUserDetails) authentication.getPrincipal();

        System.out.println(authentication.getPrincipal().toString());
        model.addAttribute("principal", authentication.getPrincipal());

        model.addAttribute("userId", loggedUserInfo.getAdditionalInfoByKey("userId"));
        model.addAttribute("firstName", loggedUserInfo.getAdditionalInfoByKey("firstName"));
        
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
