package com.chicken.dangdang.entity;

import com.chicken.dangdang.constant.Accepted;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "order")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Integer orderId;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "shop_id")
    private Shop shopId;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User userId;

    @NotNull
    @Column(name = "fried")
    private Integer fried;

    @NotNull
    @Column(name = "seasoned")
    private Integer seasoned;

    @NotNull
    @Column(name = "extra")
    private String extra;

    @Enumerated(EnumType.STRING)
    @Column(name = "accepted")
    private Accepted accepted;
}
