package com.chicken.dangdang.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "product")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @ManyToOne
    @NotNull
    @JoinColumn(name = "shop_id")
    private Shop shopId;

    @NotNull
    private Integer friedQty;

    @NotNull
    private Integer seasonedQty;
}
