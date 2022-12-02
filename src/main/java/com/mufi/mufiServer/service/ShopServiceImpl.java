package com.mufi.mufiServer.service;

import com.mufi.mufiServer.dao.ShopMapper;
import com.mufi.mufiServer.dto.ShopDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final ShopMapper shopMapper;

    @Override
    public Map<String, Object> getShopLocation() {
        Map<String, Object> map = new HashMap<>();

        try {
            ArrayList<ShopDto> ShopDtoArrayList = shopMapper.getAllShop();
            Iterator<ShopDto> itr = ShopDtoArrayList.iterator();

            ArrayList<ShopInfo> shopInfoArrayList = new ArrayList<>();

            while (itr.hasNext()) {
                ShopDto shopDto = itr.next();
                shopInfoArrayList.add(new ShopInfo(shopDto.getShop_id(),
                        shopDto.getNorth_latitude(),
                        shopDto.getEast_longitude()));
            }

            // 매장 ID, 위치 정보(위도 경도)만을 담은 map 리턴
            map.put("locations", shopInfoArrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public Map<String, Object> getShopDtoArrayList() {
        Map<String, Object> map = new HashMap<>();

        try {
            ArrayList<ShopDto> shopDtoArrayList = shopMapper.getAllShop();

            map.put("locations", shopDtoArrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public Map<String, Object> getShopInfo(String shop_id) {
        Map<String, Object> map = new HashMap<>();

        try {
            ShopDto shopDto = shopMapper.getShopInfo(shop_id);

            map.put("shop_id", shopDto.getShop_id());
            map.put("shop_name", shopDto.getShop_name());
            map.put("shop_address", shopDto.getShop_address());
            map.put("north_latitude", shopDto.getNorth_latitude());
            map.put("east_longitude", shopDto.getEast_longitude());
            map.put("open_time", shopDto.getOpen_time().toString());
            map.put("close_time", shopDto.getClose_time().toString());
            map.put("number_booth", shopDto.getNumber_booth());
            map.put("number_using_booth", shopDto.getNumber_using_booth());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @Getter
    class ShopInfo {
        private String shop_id;
        private double north_latitude;
        private double east_longitude;

        public ShopInfo(String shop_id, double north_latitude, double east_longitude) {
            this.shop_id = shop_id;
            this.north_latitude = north_latitude;
            this.east_longitude = east_longitude;
        }
    }
}
