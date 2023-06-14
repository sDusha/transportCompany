package com.example.transportcompany.services;

import com.example.transportcompany.models.Storage;
import com.example.transportcompany.models.User;
import com.example.transportcompany.repositories.StorageRepository;
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
public class StorageService {
    private final StorageRepository storageRepository;
    private final UserRepository userRepository;

    public List<Storage> listStorages(String title) {
        if (title != null) return storageRepository.findByTitle(title);
        return storageRepository.findAll();
    }

    public List<Storage> listAllStorages() {
        return storageRepository.findAll();
    }

    public void saveStorage(Storage storage) {
        log.info("Saving new {}", storage);
        storageRepository.save(storage);
    }

    public void deleteStorage(Long id) {
        storageRepository.deleteById(id);
    }

    public Storage getStorageById(Long id) {
        return storageRepository.findById(id).orElse(null);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
}