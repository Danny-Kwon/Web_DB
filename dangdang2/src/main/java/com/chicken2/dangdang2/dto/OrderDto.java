package com.chicken2.dangdang2.dto;

import com.chicken2.dangdang2.entity.Shop;
import com.chicken2.dangdang2.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    Integer orderId;
    Integer fried;
    Integer seasoned;
    String extra;
}