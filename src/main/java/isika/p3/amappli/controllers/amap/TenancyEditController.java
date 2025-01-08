package isika.p3.amappli.controllers.amap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.dto.amap.TenancyUpdateAddressDTO;
import isika.p3.amappli.dto.amap.TenancyUpdateLogo;
import isika.p3.amappli.dto.amap.TenancyUpdateNameAliasDTO;
import isika.p3.amappli.dto.amap.TenancyUpdatePickUpDTO;
import isika.p3.amappli.dto.amap.TenancyUpdateSloganDTO;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.service.amap.GraphismService;
import isika.p3.amappli.service.amappli.TenancyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/amap")
public class TenancyEditController {
    
    private final TenancyService tenancyService;

    private final GraphismService graphismService;


    public TenancyEditController(TenancyService tenancyService, GraphismService graphismService) {
        this.tenancyService = tenancyService;
        this.graphismService = graphismService;
    }
    

    @GetMapping("/{tenancyAlias}/admin/edit")    
    public String editHomePageContent(@PathVariable("tenancyAlias") String alias, Model model) {

        graphismService.setUpModel(alias,model);
        Tenancy tenancy = tenancyService.getTenancyByAlias(alias);
        // Set existing info
        // Alias and Name
        TenancyUpdateNameAliasDTO updateNameAliasDTO = new TenancyUpdateNameAliasDTO();
        updateNameAliasDTO.setTenancyAlias(tenancy.getTenancyAlias());
        updateNameAliasDTO.setTenancyName(tenancy.getTenancyName());
        model.addAttribute("tenancyUpdateNameAliasDTO",updateNameAliasDTO);

        // Slogan
        TenancyUpdateSloganDTO tenancyUpdateSloganDTO = new TenancyUpdateSloganDTO();
        tenancyUpdateSloganDTO.setSlogan(tenancy.getTenancySlogan());
        model.addAttribute("tenancyUpdateSloganDTO", tenancyUpdateSloganDTO);

        // Logo
        TenancyUpdateLogo tenancyUpdateLogo = new TenancyUpdateLogo();
        tenancyUpdateLogo.setLogoImg(tenancy.getGraphism().getLogoImg());
        tenancyUpdateLogo.setLogoImgType(tenancy.getGraphism().getLogoImgType());
        model.addAttribute("tenancyUpdateLogo", tenancyUpdateLogo);

        // Address
        TenancyUpdateAddressDTO tenancyUpdateAddressDTO = new TenancyUpdateAddressDTO();
        tenancyUpdateAddressDTO.setAddress(tenancy.getAddress());
        model.addAttribute("tenancyUpdateAddressDTO",tenancyUpdateAddressDTO);
        
        // PickUpSchedule
        TenancyUpdatePickUpDTO tenancyUpdatePickUpDTO = new TenancyUpdatePickUpDTO();
        tenancyUpdatePickUpDTO.setPickUpSchedule(tenancy.getPickUpSchedule());
        model.addAttribute("tenancyUpdatePickUpDTO", tenancyUpdatePickUpDTO);

        return "/amap/back/edit/edithomepage";
    }

    @PostMapping("/{tenancyAlias}/admin/editthenameandalias")    
    public String editNameAlias(TenancyUpdateNameAliasDTO tenancyDTO, @PathVariable("tenancyAlias") String alias, Model model) {

        tenancyService.updateTenancyNameOrAlias(tenancyDTO,alias);

        // different redirect if tenancy alias has changed
        if(!tenancyDTO.getTenancyAlias().equals(alias)) {
            return "redirect:/amap/"+ tenancyDTO.getTenancyAlias() + "/admin/edit";
        }
        return "redirect:/amap/"+ alias + "/admin/edit";
    }

    @PostMapping("/{tenancyAlias}/admin/edittheslogan")
    public String editSlogan(TenancyUpdateSloganDTO tenancyDTO, @PathVariable("tenancyAlias") String alias, Model model){
        tenancyService.updateTenancySlogan(tenancyDTO, alias);
        return "redirect:/amap/"+ alias + "/admin/edit";
    }

    @PostMapping("/{tenancyAlias}/admin/editthelogo")
    public String editLogo(TenancyUpdateLogo tenancyDTO, @PathVariable("tenancyAlias") String alias, Model model){
        tenancyService.updateTenancyLogo(tenancyDTO, alias);
        return "redirect:/amap/"+ alias + "/admin/edit";
    }

    @PostMapping("/{tenancyAlias}/admin/edittheaddress")
    public String editLogo(TenancyUpdateAddressDTO tenancyDTO, @PathVariable("tenancyAlias") String alias, Model model){
        tenancyService.updateTenancyAddress(tenancyDTO, alias);
        return "redirect:/amap/"+ alias + "/admin/edit";
    }
}
