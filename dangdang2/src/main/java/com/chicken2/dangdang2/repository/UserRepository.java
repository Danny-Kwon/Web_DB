package com.chicken2.dangdang2.repository;

import com.chicken2.dangdang2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    //email 조회 -> 중복유저 확인 및 로그인시에도 활용
    User findByEmail(String email);
    User findByName(String name);
}