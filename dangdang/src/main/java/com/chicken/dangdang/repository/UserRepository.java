package com.chicken.dangdang.repository;

import com.chicken.dangdang.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    //email 조회 -> 중복유저 확인 및 로그인시에도 활용
    User findByEmail(String email);
}
