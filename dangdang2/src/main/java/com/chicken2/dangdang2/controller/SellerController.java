package com.chicken2.dangdang2.controller;

import com.chicken2.dangdang2.dto.ProductDto;
import com.chicken2.dangdang2.dto.ShopDto;
import com.chicken2.dangdang2.dto.UserDto;
import com.chicken2.dangdang2.entity.Order;
import com.chicken2.dangdang2.entity.Shop;
import com.chicken2.dangdang2.entity.User;
import com.chicken2.dangdang2.repository.OrderRepository;
import com.chicken2.dangdang2.repository.ShopRepository;
import com.chicken2.dangdang2.repository.UserRepository;
import com.chicken2.dangdang2.service.ShopService;
import com.chicken2.dangdang2.service.UserService;
import lombok.Builder;
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
@Builder
public class SellerController {
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ShopRepository shopRepository;
    private final PasswordEncoder passwordEncoder;
    private final ShopService shopService;
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

    @GetMapping("/shopForm")
    public String newShop(Model model){
        ShopDto shopDto = new ShopDto();
        model.addAttribute("shopDto", shopDto);
        System.out.println(shopDto);
        return "seller/shopForm";
    }

    @PostMapping("/shopForm")
    public String newShop(ShopDto shopDto, Principal principal, Model model){
        User user = userRepository.findByName(principal.getName());
        try {
            Shop build = Shop.builder()
                    .branch(shopDto.getBranch())
                    .phone(shopDto.getPhone())
                    .location(shopDto.getLocation())
                    .friedQty(shopDto.getFriedQty())
                    .seasonedQty(shopDto.getSeasonedQty())
                    .shopUsers(user.getUserId())
                    .build();
            shopService.saveShop(build);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "seller/shopForm";
        }
        return "main";
    }

    @GetMapping("/shops")
    public String modifyShopQty(Model model){
        model.addAttribute("productDto", new ProductDto());
        return "/seller/shops";
    }

    @PostMapping("/shops")
    public String modifyShopQty(ProductDto productDto, Model model){
        try {
            shopService.modify(productDto.getBranch(), productDto.getMdfFried(), productDto.getMdfSeasoned());
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/seller/shops";
        }
        return "main";
    }

    @GetMapping(value = "/orderList")
    public String shopOrders(Principal principal, Model model){
        Integer user = (userRepository.findByName(principal.getName())).getUserId();
        List<Order> orderList = orderRepository.findByReceiveUser(user);
        if(orderList != null){
            model.addAttribute("orderList", orderList);
            return "seller/orderList";
        }else {
            return "seller/shops";
        }
    }

    @GetMapping(value = "/shopList")
    public String shopLists(Principal principal, Model model){
        Integer user = (userRepository.findByName(principal.getName())).getUserId();
        List<Shop> shopList = shopRepository.findByShopUsers(user);
        if (shopList != null){
            model.addAttribute("shopList", shopList);
            return "seller/shopList";
        }else {
            return "seller/shopForm";
        }
    }
}
