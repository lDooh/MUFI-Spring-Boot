package com.mufi.mufiServer.controller;

import com.mufi.mufiServer.service.ShopServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("app/shop")
@RequiredArgsConstructor
public class ShopController {
    private final ShopServiceImpl shopService;

    @GetMapping("/location")
    public Map<String, Object> getShopLocation() {
        return shopService.getShopLocation();
    }

    @GetMapping("info/{shop_id}")
    public Map<String, Object> getShopInfo(@PathVariable("shop_id") String shop_id) {
        return shopService.getShopInfo(shop_id);
    }
}
