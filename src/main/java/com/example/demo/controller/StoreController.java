package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.demo.domain.Store;
import com.example.demo.service.StoreService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StoreController {
    
    private final StoreService storeService;

    @GetMapping("/stores/new")
    public String createForm(Model model) {
        model.addAttribute("storeForm", new StoreForm());
        return "stores/createStoreForm";
    }

    @PostMapping("/stores/new")
    public String create(@Valid StoreForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "stores/createStoreForm";
        }

        Store store = new Store();
        store.setStoreName(form.getName());
        
        storeService.join(store);
        return "redirect:/";
    }

    @GetMapping("/stores")
    public String list(Model model) {
        List<Store> stores = storeService.findStores();
        model.addAttribute("stores", stores);
        return "stores/StoreList";
    }
}
