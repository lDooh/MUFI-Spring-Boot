package com.mufi.mufiServer.service;

import java.util.Map;

public interface ShopService {
    public Map<String, Object> getShopLocation();

    public Map<String, Object> getShopInfo(String shop_id);
}
