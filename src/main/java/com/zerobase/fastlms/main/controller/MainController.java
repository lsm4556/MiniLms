package com.zerobase.fastlms.main.controller;


import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.repository.BannerRepository;
import com.zerobase.fastlms.admin.entity.BannerTargetType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final BannerRepository bannerRepository;
    
    @RequestMapping("/")
    public String index(Model model) {
        
        List<BannerDto> banners = bannerRepository.findAllByVisibleIsTrueOrderBySortValue().stream()
                .map(BannerDto::of)
                .collect(Collectors.toList());
        model.addAttribute("banners", banners);
        return "index";
    }
    
    
    
    @RequestMapping("/error/denied")
    public String errorDenied() {
        
        return "error/denied";
    }
    
    
    
}
