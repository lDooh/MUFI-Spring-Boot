package com.mufi.mufiServer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class QrServiceImpl implements QrService {

    @Override
    public Map<String, Object> qrScanning(String id, String kiosk) {
        Map<String, Object> map = new HashMap<>();
        
        map.put("isQrSuccess", 1);
        
        /* 키오스크에 유저 ID를 전달하는데 성공하면 1 리턴
        * */

        return map;
    }
}
