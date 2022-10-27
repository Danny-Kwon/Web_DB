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
    private Integer fried;

    @NotNull
    private Integer seasoned;

    @NotNull
    private String extra;

    @Enumerated(EnumType.STRING)
    private Accepted accepted;
}
