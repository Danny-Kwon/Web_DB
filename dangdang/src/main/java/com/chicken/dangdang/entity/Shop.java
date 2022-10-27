package com.chicken.dangdang.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "shop")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    @Id
    @Column(name = "shop_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer shopId;

    @NotNull
    @Column(unique = true)
    private String branch;

    @NotNull
    private String location;

    @NotNull
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User userId;
}
