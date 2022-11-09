package com.chicken2.dangdang2.entity;

import com.chicken2.dangdang2.dto.ShopDto;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shop")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Shop implements Serializable {
    @Id
    @Column(name = "shop_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer shopId;

    @NotNull
    @Column(name = "branch", unique = true)
    private String branch;

    @NotNull
    @Column(name = "location")
    private String location;

    @NotNull
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "fried_qty")
    private Integer friedQty;

    @NotNull
    @Column(name = "seasoned_qty")
    private Integer seasonedQty;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    public static Shop createBranch(ShopDto shopDto, User user){
        Shop shop = new Shop();
        shop.setBranch(shop.getBranch());
        shop.setLocation(shop.getPhone());
        shop.setPhone(shop.getPhone());
        shop.setUser(user);
        shop.setFriedQty(shop.getFriedQty());
        shop.setSeasonedQty(shop.getSeasonedQty());
        return shop;
    }
}