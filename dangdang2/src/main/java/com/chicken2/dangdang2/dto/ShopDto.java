package com.chicken2.dangdang2.dto;

import com.chicken2.dangdang2.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class ShopDto {
    @NotBlank(message = "지점명은 필수 입력 값입니다.")
    String branch;

    @NotEmpty(message = "위치는 필수 입력 값입니다.")
    String location;

    @NotEmpty(message = "전화번호는 필수 입력 값입니다.")
    String phone;
    User user;
    Integer friedQty;
    Integer seasonedQty;
}