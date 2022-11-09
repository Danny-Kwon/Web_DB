package com.chicken2.dangdang2.repository;

import com.chicken2.dangdang2.dto.ShopDto;
import com.chicken2.dangdang2.dto.UserDto;
import com.chicken2.dangdang2.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByShop(ShopDto shopDto);
    List<Order> findByUser(UserDto userDto);
}
