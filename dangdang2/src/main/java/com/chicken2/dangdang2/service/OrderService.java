package com.chicken2.dangdang2.service;

import com.chicken2.dangdang2.entity.Order;
import com.chicken2.dangdang2.entity.Shop;
import com.chicken2.dangdang2.repository.OrderRepository;
import com.chicken2.dangdang2.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ShopRepository shopRepository;

    public Order saveOrder(Order order){
        validateExistShop(order);
        return orderRepository.save(order);
    }

    private void validateExistShop(Order order) {
        String branch = order.getShop().getBranch();
        Shop findShop = shopRepository.findByBranch(branch);
        if(findShop == null){
            throw new IllegalStateException("존재하지 않는 지점입니다.");
        }
    }
}
