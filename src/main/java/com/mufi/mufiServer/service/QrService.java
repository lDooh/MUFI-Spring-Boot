package com.mufi.mufiServer.service;

import java.util.Map;

public interface QrService {
    public Map<String, Object> qrScanning(String id, String kiosk);
}
