package com.chicken2.dangdang2.repository;

import com.chicken2.dangdang2.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findByBranch(String branch); // 지점명으로 검색
    Shop findByShopId(Integer shopId);
    List<Shop> findByShopUsers(Integer shopUsers);
}