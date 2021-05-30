package com.example.demo.service;

import com.example.demo.domain.Delivery;
import com.example.demo.domain.Order;
import com.example.demo.domain.OrderItem;
import com.example.demo.domain.Store;
import com.example.demo.domain.item.Item;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.StoreRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long storeId, Long itemId) {

        Store store = storeRepository.findOne(storeId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice());

        Order order = Order.createOrder(store, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void existlOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.exist();
    }

}
