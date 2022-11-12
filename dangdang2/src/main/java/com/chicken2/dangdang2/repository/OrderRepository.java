package com.chicken2.dangdang2.repository;

import com.chicken2.dangdang2.entity.Order;
import com.chicken2.dangdang2.entity.Shop;
import com.chicken2.dangdang2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByShop(Shop shop);
    List<Order> findByUser(User user);
}
