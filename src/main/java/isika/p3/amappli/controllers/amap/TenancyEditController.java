package isika.p3.amappli.controllers.amap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.dto.amap.TenancyUpdateNameAliasDTO;
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
    

    @GetMapping("/{tenancyAlias}/admin/edithomepage")    
    public String editHomePageContent(@PathVariable("tenancyAlias") String alias, Model model) {

        graphismService.setUpModel(alias,model);
        model.addAttribute("tenancyUpdateNameAliasDTO",new TenancyUpdateNameAliasDTO());
        model.addAttribute("tenancyAlias",alias);
        
        return "/amap/back/edit/edithomepage";
    }

    @PostMapping("/{tenancyAlias}/admin/editthenameandalias")    
    public String editHomePageContent(TenancyUpdateNameAliasDTO tenancyDTO, @PathVariable("tenancyAlias") String alias, Model model) {

        return "redirect:/amap/"+ alias + "/back/edit/edithomepage";
    }

}
