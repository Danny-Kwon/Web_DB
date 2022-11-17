package com.chicken2.dangdang2.controller;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@Builder
public class MainController {
    @GetMapping("/")
    public String index(Model model){
        return "main";
    }
}