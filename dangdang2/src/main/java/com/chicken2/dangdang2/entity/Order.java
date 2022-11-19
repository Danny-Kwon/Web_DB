package com.chicken2.dangdang2.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "order_table")
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer orderId;

    @NotNull
    @Column(name = "orderShop")
    private Integer orderShop;

    @NotNull
    @Column(name = "orderUser")
    private Integer orderUser;

    @NotNull
    @Column(name = "receiveUser")
    private Integer receiveUser;

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