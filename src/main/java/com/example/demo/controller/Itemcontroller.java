package com.example.demo.controller;

import java.util.List;

import com.example.demo.domain.item.Food;
import com.example.demo.domain.item.Item;
import com.example.demo.service.ItemService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class Itemcontroller {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createform(Model model) {
        model.addAttribute("form", new FoodForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(FoodForm form) {
        Food food = new Food();
        food.setName(form.getName());
        food.setPrice(form.getPrice());
        food.setManufacturer(form.getManufacturer());
        food.setNutritionInformation(form.getNutritionInformation());

        itemService.saveItem(food);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model) {
        Food item = (Food) itemService.findOne(itemId);

        FoodForm form = new FoodForm();
        form.setId(item.getId());
        form.setName(form.getName());
        form.setPrice(form.getPrice());
        form.setManufacturer(form.getManufacturer());
        form.setNutritionInformation(form.getNutritionInformation());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }
    
    @PostMapping("items/{itemId}/edit")
    public String updateItem(@ModelAttribute("form") FoodForm form, @PathVariable Long itemId) {

        itemService.updateItem(itemId, form.getName(), form.getPrice());
        
        return "redirect:/items";
    }
}
