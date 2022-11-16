package com.chicken2.dangdang2.repository;

import com.chicken2.dangdang2.entity.Shop;
import com.chicken2.dangdang2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, String> {
    Shop findByBranch(String branch); // 지점명으로 검색
    Shop findByUser(User user);
}
