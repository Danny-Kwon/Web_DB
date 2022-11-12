package com.chicken2.dangdang2.controller;

import com.chicken2.dangdang2.dto.UserDto;
import com.chicken2.dangdang2.entity.Order;
import com.chicken2.dangdang2.entity.Shop;
import com.chicken2.dangdang2.entity.User;
import com.chicken2.dangdang2.repository.OrderRepository;
import com.chicken2.dangdang2.repository.ShopRepository;
import com.chicken2.dangdang2.repository.UserRepository;
import com.chicken2.dangdang2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;


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

    @GetMapping(value = "/main") // 지점명 검색하면 해당 지점 shop 인스턴스 보여줌
    public String userShop(@RequestParam(value = "branch", required = false) String branch, Model model){
        List<Shop> shopList = shopRepository.findByBranch(branch);
        model.addAttribute("shopList", shopList);
        return "user/newOrder";
    }

    @GetMapping(value = "/orders") // user ID를 통해 볼 수 있는 주문목록
    public String userOrder(@RequestParam(value = "email", required = false) String email, Model model){
        User user = userRepository.findByEmail(email);
        List<Order> orderList = orderRepository.findByUser(user);
        model.addAttribute("orderList", orderList);
        return "user/orders";
    }
}
