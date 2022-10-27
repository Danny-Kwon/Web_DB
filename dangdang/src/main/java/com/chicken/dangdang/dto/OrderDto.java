package com.chicken.dangdang.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    Integer orderId;
    Integer userId;
    Integer shopId;
    Integer fried;
    Integer seasoned;
    String extra;
}
