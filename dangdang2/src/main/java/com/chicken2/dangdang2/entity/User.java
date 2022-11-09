package com.chicken2.dangdang2.entity;

import com.chicken2.dangdang2.constant.Role;
import com.chicken2.dangdang2.dto.UserDto;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_table")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    public static User createUser(UserDto userDto, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        String password = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(password);
        user.setRole(Role.CLIENT);
        return user;
    }
    public static User createSeller(UserDto userDto, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        String password = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(password);
        user.setRole(Role.SELLER);
        return user;
    }
}