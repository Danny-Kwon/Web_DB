package com.chicken2.dangdang2.service;

import com.chicken2.dangdang2.entity.Shop;
import com.chicken2.dangdang2.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;

    public void saveShop(Shop shop){
        validateDuplicateShop(shop);
        shopRepository.save(shop);
    }

    private void validateDuplicateShop(Shop shop) {
        Shop findShop = shopRepository.findByBranch(shop.getBranch());
        if(findShop != null){
            throw new IllegalStateException("이미 등록된 지점입니다.");
        }
    }
}
