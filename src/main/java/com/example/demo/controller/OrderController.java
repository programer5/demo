package com.example.demo.controller;

import java.util.List;

import com.example.demo.domain.Store;
import com.example.demo.domain.item.Item;
import com.example.demo.service.ItemService;
import com.example.demo.service.OrderService;
import com.example.demo.service.StoreService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;
    private final StoreService storeService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createFrom(Model model) {

        List<Store> stores = storeService.findStores();
        List<Item> items = itemService.findItems();

        model.addAttribute("stores", stores);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("storeId") Long storeId,
                        @RequestParam("itemId") Long itemId) {
        
        orderService.order(storeId, itemId);
        return "redirect:/orders";
    }

    @PostMapping("/orders/{orderId}/exist")
    public String existOrder(@PathVariable("orderId") Long orderId) {
        orderService.existlOrder(orderId);
        return "redirect:/orders";
    }
}
