package com.mufi.mufiServer.service;

import java.util.Map;

public interface QrService {
    public Map<String, Object> qrScanning(String kiosk_ip, String shop_id, int kiosk_number, String user_id);
}
