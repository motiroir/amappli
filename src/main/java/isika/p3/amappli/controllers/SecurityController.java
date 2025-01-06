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

import isika.p3.amappli.entities.tenancy.ColorPalette;
import isika.p3.amappli.entities.tenancy.FontChoice;
import isika.p3.amappli.entities.tenancy.Graphism;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.security.CustomUserDetails;
import isika.p3.amappli.service.amap.GraphismService;
import isika.p3.amappli.service.amappli.TenancyService;

@Controller
public class SecurityController {
    
    private final GraphismService graphismService;
    private final TenancyService tenancyService;


    public SecurityController(GraphismService graphismService, TenancyService tenancyService) {
        this.graphismService = graphismService;
        this.tenancyService = tenancyService;
    }
  

    @GetMapping("/login")
    public String goToLogin(@RequestParam(name = "error", required = false) String error,Model model){
        
        if (error != null) {
            model.addAttribute("error", "Email ou mot de passe incorrect.");
        }

        model.addAttribute("mapStyleLight", "mapbox://styles/tiroirmorgane/cm4sw37wr001301s12frm2l2y");
        model.addAttribute("mapStyleDark", "mapbox://styles/tiroirmorgane/cm52cqefg003101sa878udky6");
        model.addAttribute("cssStyle", ColorPalette.PALETTE1);
        model.addAttribute("font", FontChoice.FUTURA);

        return "amappli/front/amapplilogin";
    }

    @GetMapping("/{tenancyAlias}/login")
    public String goToTenancyLogin(@RequestParam(name = "error", required = false) String error, @PathVariable("tenancyAlias") String alias, Model model) {

        // LoginDTO loginDTO = new LoginDTO();
        // model.addAttribute("loginDTO", loginDTO);
        model.addAttribute("tenancyAlias", alias);
        if (error != null) {
            model.addAttribute("error", "Email ou mot de passe incorrect.");
        }
        Tenancy tenancy = tenancyService.getTenancyByAlias(alias);
        
        // Ajouter les informations générales de la tenancy
        model.addAttribute("tenancy", tenancy);
        model.addAttribute("tenancyName", tenancy.getTenancyName());
        model.addAttribute("tenancySlogan", tenancy.getTenancySlogan());
        
     // Ajouter les informations graphiques
        Graphism graphism = tenancy.getGraphism();
        String logoBase64 = graphism != null ? graphism.getLogoImg() : null;
        String logoImgType = graphism != null ? graphism.getLogoImgType() : null;
        model.addAttribute("logoBase64", logoBase64);
        model.addAttribute("logoImgType", logoImgType);
        
        // Récupérer et ajouter les styles dynamiques via GraphismService
        model.addAttribute("mapStyleLight", graphismService.getMapStyleLightByTenancyAlias(alias));
        model.addAttribute("mapStyleDark", graphismService.getMapStyleDarkByTenancyAlias(alias));
        model.addAttribute("cssStyle", graphismService.getColorPaletteByTenancyAlias(alias));
        model.addAttribute("font", graphismService.getFontByTenancyAlias(alias));

        return "amap/amaplogin/login";
        //return "secexamples/tenantlogin";
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
