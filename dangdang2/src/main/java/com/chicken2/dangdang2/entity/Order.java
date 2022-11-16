package com.chicken2.dangdang2.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "order")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer orderId;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "fried")
    private Integer fried;

    @NotNull
    @Column(name = "seasoned")
    private Integer seasoned;

    @NotNull
    @Column(name = "extra")
    private String extra;
}