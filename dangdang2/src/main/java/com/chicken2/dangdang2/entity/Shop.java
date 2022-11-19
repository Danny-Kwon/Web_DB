package com.chicken2.dangdang2.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shop_table")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shop implements Serializable {
    @Id
    @Column(name = "shop_Id")
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

    @NotNull
    @Column(name = "shopUsers")
    private Integer shopUsers;
}