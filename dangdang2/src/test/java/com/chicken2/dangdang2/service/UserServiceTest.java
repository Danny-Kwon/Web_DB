package com.chicken2.dangdang2.service;

import com.chicken2.dangdang2.dto.UserDto;
import com.chicken2.dangdang2.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createUser(){
        UserDto userDto = new UserDto();
        userDto.setName("홍길동");
        userDto.setEmail("test@email.com");
        userDto.setPassword("1234");
        return User.createUser(userDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveUser() {
        User user = createUser();
        User savedUser = userService.saveUser(user);
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getPassword(), savedUser.getPassword());
        assertEquals(user.getRole(), savedUser.getRole());
    }

    @Test
    void loadUserByUsername() {
    }
}