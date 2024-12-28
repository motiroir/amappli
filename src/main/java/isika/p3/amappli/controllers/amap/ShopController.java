package isika.p3.amappli.controllers.amap;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.service.amap.ContractService;

@Controller
@RequestMapping("/shop")
public class ShopController {
	
    private final ContractService contractService;

    public ShopController(ContractService contractService) {
        this.contractService = contractService;
    }

    /**
     * Displays all shoppable contracts in the shop view.
     */
    @GetMapping("/contracts")
    public String showAllShoppableContracts(Model model) {
        List<Contract> contracts = contractService.findAll(); // Récupère tous les contrats depuis le service
        contracts.removeIf(contract -> !contract.isShoppable()); // Filtre les contrats non shoppables
        model.addAttribute("contracts", contracts); // Ajoute la liste des contrats shoppables au modèle
        return "amap/front/shop-contracts"; // Retourne la vue shop-contracts.jsp
    }

}
