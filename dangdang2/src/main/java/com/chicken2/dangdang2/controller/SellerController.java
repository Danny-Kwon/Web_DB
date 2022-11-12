package com.chicken2.dangdang2.controller;

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

@RequestMapping("/seller")
@Controller
@RequiredArgsConstructor
public class SellerController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final OrderRepository orderRepository;
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;

    @GetMapping(value = "/new")
    public String newSeller(Model model){
        model.addAttribute("userDto", new UserDto());
        return "seller/new";
    }

    @PostMapping(value = "/new")
    public String newSeller(@Valid UserDto userDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "seller/new";
        }

        try {
            User user = User.createSeller(userDto, passwordEncoder);
            userService.saveUser(user);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "seller/new";
        }

        return "main";
    }

    @PostMapping(value = "/newShop")
    public String newShop(@Valid ShopDto shopDto, @RequestParam(value = "email", required = false) String email, Model model){
        User user = userRepository.findByEmail(email);
        List<Shop> shopList = shopRepository.findByUser(user);
        model.addAttribute("shopList", shopList);
        return "seller/shops";
    }

    @GetMapping(value = "/orders")
    public String shopOrders(@RequestParam(value = "branch", required = false)String branch, Model model){
        Shop shop = (Shop) shopRepository.findByBranch(branch);
        List<Order> orderList = orderRepository.findByShop(shop);
        return "seller/orders";
    }
}
