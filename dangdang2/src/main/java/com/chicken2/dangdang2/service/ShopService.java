package com.chicken2.dangdang2.service;

import com.chicken2.dangdang2.entity.Order;
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

    public void modify(String branch, Integer fried, Integer seasoned){
        Shop shop = shopRepository.findByBranch(branch);
        validateExistShop(shop);
        shop.setFriedQty(fried);
        shop.setSeasonedQty(seasoned);
    }
    public void modifyQty(Shop shop, Order order){
        Integer currentFried = shop.getFriedQty();
        Integer currentSeasoned = shop.getSeasonedQty();
        Integer orderFried = order.getFried();
        Integer orderSeasoned = order.getSeasoned();
        if ((currentFried < orderFried) || (currentSeasoned < orderSeasoned)){
            order.setExtra("주문 실패");
            throw new NumberFormatException("주문하고자 하는 수량이 재고보다 많습니다.");
        }else {
            shop.setFriedQty(currentFried - orderFried);
            shop.setSeasonedQty(currentSeasoned - orderSeasoned);
        }
    }

    private void validateDuplicateShop(Shop shop) {
        Shop findShop = shopRepository.findByBranch(shop.getBranch());
        if(findShop != null){
            throw new IllegalStateException("이미 등록된 지점입니다.");
        }
    }

    private void validateExistShop(Shop shop) {
        Shop findShop = shopRepository.findByBranch(shop.getBranch());
        if(findShop == null){
            throw new IllegalStateException("이미 등록된 지점입니다.");
        }
    }
}
