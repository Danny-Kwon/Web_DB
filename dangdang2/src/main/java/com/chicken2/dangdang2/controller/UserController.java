package com.chicken2.dangdang2.controller;

import com.chicken2.dangdang2.dto.OrderDto;
import com.chicken2.dangdang2.dto.ShopDto;
import com.chicken2.dangdang2.dto.UserDto;
import com.chicken2.dangdang2.entity.Order;
import com.chicken2.dangdang2.entity.Shop;
import com.chicken2.dangdang2.entity.User;
import com.chicken2.dangdang2.repository.OrderRepository;
import com.chicken2.dangdang2.repository.ShopRepository;
import com.chicken2.dangdang2.repository.UserRepository;
import com.chicken2.dangdang2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;

    @GetMapping(value = "/new")
    public String newUser(Model model){
        model.addAttribute("userDto", new UserDto());
        return "user/new";
    }


    @PostMapping(value = "/new")
    public String newUser(@Valid UserDto userDto, BindingResult bindingResult, Model model){
        log.info("userDto{}{}{}", userDto.getEmail(), userDto.getPassword(), userDto.getName());
        if(bindingResult.hasErrors()){
            return "user/new";
        }

        try {
            User user = User.createUser(userDto, passwordEncoder);
            userService.saveUser(user);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "user/new";
        }

        return "main";
    }

    @GetMapping(value = "/login")
    public String loginUser(){
        return "user/login";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "user/login";
    }

    @GetMapping(value = "/shop") // 지점명 검색하면 해당 지점 주문화면 보여줌
    public String userShop(String branch, Model model){
        Shop shop = shopRepository.findByBranch(branch);
        ShopDto shopDto = new ShopDto();
        shopDto.setBranch(shop.getBranch());
        shopDto.setLocation(shop.getLocation());
        shopDto.setPhone(shop.getPhone());
        shopDto.setFriedQty(shop.getFriedQty());
        shopDto.setSeasonedQty(shop.getSeasonedQty());
        model.addAttribute("shopDto", shopDto);
        return "user/shop";
    }

    @PostMapping(value = "/order")
    public String userShop(OrderDto orderDto, Model model){

        Order order = Order.builder()
                .fried(orderDto.getFried())
                .seasoned(orderDto.getSeasoned())
                .shop((Shop) model.getAttribute("shop")).build();
        orderRepository.save(order);
        return "main";
    }

    @GetMapping(value = "/orderList")
    public String orderList(Principal principal, Model model){
        String name = principal.getName();
        User user = userRepository.findByName(name);
        List<Order> order = orderRepository.findByUser(user);
        model.addAttribute("orderList", order);
        return "user/orderList";
    }
}
