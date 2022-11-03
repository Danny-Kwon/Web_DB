package com.chicken.dangdang.repository;

import com.chicken.dangdang.DangdangApplicationTests;
import com.chicken.dangdang.constant.Access;
import com.chicken.dangdang.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserRepositoryTest extends DangdangApplicationTests {

    @Autowired
    UserRepository userRepository;

    public User createClient(){
        User user = new User();
        user.setName("권민혁");
        user.setEmail("danny9kwon@gmail.com");
        user.setPassword("1234");
        user.setAccess(Access.CLIENT);
        return user;
    }

    @Transactional
    @Test
    @DisplayName("로그인 기능 테스트")
    public void findByEmail() {
        User user1 = this.createClient();
        userRepository.save(user1);
        Assert.notEmpty((Collection<?>) user1, "사용자 정보가 존재하지 않습니다.");
    }
}