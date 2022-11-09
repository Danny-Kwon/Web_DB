package com.chicken2.dangdang2.repository;

import com.chicken2.dangdang2.entity.Shop;
import com.chicken2.dangdang2.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class ShopRepositoryTest {

    @Autowired
    ShopRepository shopRepository;

    public void createShopList(){
        for(int i = 1; i <= 10; i++){
            Shop shop = new Shop();
            shop.setShopId(i);
            shop.setBranch("가천"+ i +"호점");
            shop.setLocation("가천"+ i + "길");
            shop.setPhone("010-1234-567"+i);
            shop.setFriedQty(i);
            shop.setSeasonedQty(i);
            shopRepository.save(shop);
        }
    }

    @Test
    @DisplayName("지점명 검색 테스트")
    public void findByBranch() {
        this.createShopList();
        List<Shop> shopList = shopRepository.findByBranch("가천1호점");
        for(Shop shop: shopList){
            System.out.println(shop.toString());
        }
    }
}