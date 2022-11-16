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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Slf4j
@RequestMapping("/seller")
@Controller
@RequiredArgsConstructor
public class SellerController {
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ShopRepository shopRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
            model.addAttribute("currentUser", user);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "seller/new";
        }

        return "main";
    }

    @GetMapping("/shopForm")
    public String newShop(Model model){
        model.addAttribute("shopDto", new ShopDto());
        return "seller/shopForm";
    }

    @PostMapping("/shopForm")
    public String newShop(ShopDto shopDto, Model model){
        User user = (User) model.getAttribute("currentUser");
        Shop build = Shop.builder()
                .branch(shopDto.getBranch())
                .phone(shopDto.getPhone())
                .location(shopDto.getLocation())
                .friedQty(shopDto.getFriedQty())
                .seasonedQty(shopDto.getSeasonedQty())
                .user(user)
                .build();
        shopRepository.save(build);
        model.addAttribute("branch", shopDto.getBranch());
        return "main";
    }

    @GetMapping("/shops")
    public String index(Model model){
        return "/seller/shops";
    }

//    @ResponseBody
//    @GetMapping(value = "/newShop")
//    public String newShop(Principal principal){
//        log.info("{}", principal.getName());
//        return "123";
//
//        User user = userRepository.findByEmail(email);
//        List<Shop> shopList = shopRepository.findByUser(user);
//        model.addAttribute("shopList", shopList);
//        return "seller/shops";}

    @GetMapping(value = "/orders")
    public String shopOrders(@RequestParam(value = "branch", required = false)String branch, Model model){
        Shop shop = (Shop) shopRepository.findByBranch(branch);
        List<Order> orderList = orderRepository.findByShop(shop);
        return "seller/orders";
    }
}
