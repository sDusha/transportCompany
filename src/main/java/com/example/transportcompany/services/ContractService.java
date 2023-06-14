package com.example.transportcompany.services;

import com.example.transportcompany.models.Contract;
import com.example.transportcompany.models.User;
import com.example.transportcompany.repositories.ContractRepository;
import com.example.transportcompany.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContractService {
    private final ContractRepository contractRepository;
    private final UserRepository userRepository;

    public List<Contract> listContracts(User user) {
        if (user != null) return contractRepository.findByUser(user);
        return null;
    }

    public List<Contract> listAllContracts() {
        return contractRepository.findAll();
    }

    public void saveContract(Principal principal, Contract contract) throws IOException {

        contract.setUser(getUserByPrincipal(principal));
        log.info("Saving new {}", contract);
        contractRepository.save(contract);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void deleteContract(Long id) {
        contractRepository.deleteById(id);
    }

    public Contract getContractById(Long id) {
        return contractRepository.findById(id).orElse(null);
    }

}
