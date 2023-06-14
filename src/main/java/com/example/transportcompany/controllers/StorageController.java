package com.example.transportcompany.controllers;

import com.example.transportcompany.models.Storage;
import com.example.transportcompany.models.User;
import com.example.transportcompany.services.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.security.Principal;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StorageController {
    private final StorageService storageService;

    @GetMapping(value={"/","/storages"})
    public String storages(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        User user = storageService.getUserByPrincipal(principal);
        model.addAttribute("storages", storageService.listStorages(title));
        model.addAttribute("user", user);
        return "storages";
    }

    @GetMapping("/storage/{id}")
    public String storageInfo(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("user", storageService.getUserByPrincipal(principal));
        model.addAttribute("storage", storageService.getStorageById(id));
        return "storage_info";
    }

    @PostMapping("/storage/create")
    public String storageCreate(Storage storage) throws IOException {
        System.out.println(storage + "123");
        storageService.saveStorage(storage);
        return "redirect:/storages";
    }

    @PostMapping("/storage/delete/{id}")
    public String storageDelete(@PathVariable Long id) throws IOException {
        storageService.deleteStorage(id);
        return "redirect:/storages";
    }
}
