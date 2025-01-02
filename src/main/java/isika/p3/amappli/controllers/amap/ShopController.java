package isika.p3.amappli.controllers.amap;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import isika.p3.amappli.entities.contract.Contract;
import isika.p3.amappli.entities.contract.ContractType;
import isika.p3.amappli.entities.tenancy.Tenancy;
import isika.p3.amappli.repo.amappli.TenancyRepository;
import isika.p3.amappli.service.amap.ContractService;

@Controller
@RequestMapping("/{tenancyAlias}/shop")
public class ShopController {

    private final ContractService contractService;
    private final TenancyRepository tenancyRepository;

    public ShopController(ContractService contractService, TenancyRepository tenancyRepository) {
        this.contractService = contractService;
        this.tenancyRepository = tenancyRepository;
    }

    /**
     * Displays all shoppable contracts in the shop view.
     */
    @GetMapping("/contracts")
    public String showAllShoppableContracts(Model model, @PathVariable("tenancyAlias") String tenancyAlias) {
        Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
                .orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));
        
        List<Contract> contracts = contractService.findShoppableContractsByTenancy(tenancy);
        model.addAttribute("contracts", contracts);
        model.addAttribute("tenancyAlias", tenancyAlias);

        return "amap/front/shop-contracts";
    }

    /**
     * Displays details for a specific contract by name.
     */
    @GetMapping("/contracts/{contractName}")
    public String showContractDetails(@PathVariable("tenancyAlias") String tenancyAlias,
                                      @PathVariable("contractName") String contractName,
                                      Model model) {
        Tenancy tenancy = tenancyRepository.findByTenancyAlias(tenancyAlias)
                .orElseThrow(() -> new IllegalArgumentException("Tenancy not found for alias: " + tenancyAlias));
        
        List<Contract> contracts = contractService.findShoppableContractsByTenancy(tenancy);
        Contract contract = contracts.stream()
                .filter(c -> c.getContractName().equalsIgnoreCase(contractName))
                .findFirst()
                .orElse(null);

        if (contract == null) {
            return "redirect:/" + tenancyAlias + "/shop/contracts";
        }
        
        // Calcul de la date de première livraison
        LocalDate today = LocalDate.now();
        DayOfWeek deliveryDayOfWeek = DayOfWeek.valueOf(contract.getDeliveryDay().name());
        LocalDate nextDeliveryDate = today.with(TemporalAdjusters.nextOrSame(deliveryDayOfWeek));

        // Si le jour de livraison correspond à aujourd'hui, passe à la semaine suivante
        if (today.getDayOfWeek() == deliveryDayOfWeek) {
            nextDeliveryDate = today.with(TemporalAdjusters.next(deliveryDayOfWeek));
        }


        model.addAttribute("contract", contract);
        model.addAttribute("nextDeliveryDate", nextDeliveryDate);
        return "amap/front/shopping-contract-detail";
    }
}
