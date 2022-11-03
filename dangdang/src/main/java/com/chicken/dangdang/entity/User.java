package com.chicken.dangdang.entity;

import com.chicken.dangdang.constant.Access;
import com.chicken.dangdang.dto.UserDto;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="email", unique = true)
    private String email;

    @NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="access")
    @Enumerated(EnumType.STRING)
    private Access access;

    // 고객 회원 가입
    public static User createClient(UserDto userDto, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        String password = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(password);
        user.setAccess(Access.CLIENT);
        return user;
    }

    // 판매자 회원 가입
    public static User createSeller(UserDto userDto, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        String password = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(password);
        user.setAccess(Access.SELLER);
        return user;
    }
}
