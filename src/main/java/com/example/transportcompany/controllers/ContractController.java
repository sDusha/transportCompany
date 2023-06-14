package com.example.transportcompany.controllers;

import com.example.transportcompany.models.Contract;
import com.example.transportcompany.models.User;
import com.example.transportcompany.models.enums.ContractStatus;
import com.example.transportcompany.models.enums.Special;
import com.example.transportcompany.services.ContractService;
import com.example.transportcompany.services.StorageService;
import com.example.transportcompany.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;


@Slf4j
@Controller
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;
    private final StorageService storageService;
    private final UserService userService;

    @GetMapping("/contracts")
    public String contracts(@RequestParam(name = "title", required = false) String title, Principal principal, Model model){
        User user = contractService.getUserByPrincipal(principal);
        if (user.isAdmin() || user.isOwner()){
            model.addAttribute("contracts", contractService.listAllContracts());
        }
        else{
            model.addAttribute("contracts", contractService.listContracts(user));
        }
        model.addAttribute("storages", storageService.listAllStorages());
        model.addAttribute("specials", Special.values());
        model.addAttribute("user", user);
        return "contracts";
    }

    @PostMapping("/contracts")
    public String contract(@RequestParam(name = "phone")String phone, Contract contract, Principal principal) throws IOException {
        return "redirect:/contracts/" + phone;
    }

    @GetMapping("/contracts/{user_phone}")
    public String userContracts(@PathVariable String user_phone, Model model, Principal principal) throws IOException {
        User user = contractService.getUserByPrincipal(principal);
        User obj = userService.findByPhoneNumber(user_phone);
        model.addAttribute("contracts", contractService.listContracts(obj));
        model.addAttribute("user", user);
        return "contracts";
    }

    @GetMapping("/contract/{id}")
    public String contractInfo(@PathVariable Long id, Model model, Principal principal){
        model.addAttribute("contract",contractService.getContractById(id));
        model.addAttribute("user", contractService.getUserByPrincipal(principal));
        model.addAttribute("statuses", ContractStatus.values());
        return "contract_info";
    }

    @PostMapping("/contract/create")
    public String contractCreate(Contract contract, Principal principal) throws IOException {
        contractService.saveContract(principal, contract);
        return "redirect:/contracts";
    }

    @PostMapping("/contract/delete/{id}")
    public String contractDelete(@PathVariable Long id) throws IOException {
        contractService.deleteContract(id);
        return "redirect:/contracts";
    }
}
