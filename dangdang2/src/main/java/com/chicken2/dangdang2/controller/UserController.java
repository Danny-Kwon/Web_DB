package com.chicken2.dangdang2.controller;

import com.chicken2.dangdang2.dto.OrderDto;
import com.chicken2.dangdang2.dto.UserDto;
import com.chicken2.dangdang2.entity.Order;
import com.chicken2.dangdang2.entity.Shop;
import com.chicken2.dangdang2.entity.User;
import com.chicken2.dangdang2.repository.OrderRepository;
import com.chicken2.dangdang2.repository.ShopRepository;
import com.chicken2.dangdang2.repository.UserRepository;
import com.chicken2.dangdang2.service.OrderService;
import com.chicken2.dangdang2.service.ShopService;
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
    private final OrderService orderService;
    private final ShopService shopService;

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

    @GetMapping(value = "/order")
    public String userOrder(Model model){
        model.addAttribute("orderDto", new OrderDto());
        return "user/order";
    }

    @PostMapping(value = "/order")
    public String userOrder(OrderDto orderDto, Principal principal, Model model){
        Shop shop = shopRepository.findByBranch(orderDto.getBranch());
        User user = userRepository.findByName(principal.getName());
        try {
            Order order = Order.builder()
                    .fried(orderDto.getFried())
                    .seasoned(orderDto.getSeasoned())
                    .extra("주문성공")
                    .orderShop(shop.getShopId())
                    .orderUser(user.getUserId())
                    .receiveUser(shop.getShopUsers())
                    .build();
            try {
                shopService.modifyQty(shop, order);
            } catch (NumberFormatException e){
                model.addAttribute("errorMessage", e.getMessage());
            }
            orderService.saveOrder(order);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "main";
    }

    @GetMapping(value = "/orderList")
    public String orderList(Principal principal, Model model){
        String name = principal.getName();
        Integer user = userRepository.findByName(name).getUserId();
        List<Order> orderList = orderRepository.findByOrderUser(user);
        model.addAttribute("orderList", orderList);
        return "user/orderList";
    }
}
