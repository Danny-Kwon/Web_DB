package com.chicken2.dangdang2.repository;

import com.chicken2.dangdang2.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderUser(Integer orderUser);
    List<Order> findByReceiveUser(Integer receiveUser);
}
