package com.mufi.mufiServer.controller;

import com.mufi.mufiServer.service.QrService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("app/qr")
@RequiredArgsConstructor
public class QrController {
    private final QrService qrService;

    @GetMapping("/{kiosk_ip}/{shop_id}/{kiosk_number}/{user_id}")
    public Map<String, Object> qrScanning(@PathVariable("kiosk_ip") String kiosk_ip,
                                          @PathVariable("shop_id") String shop_id,
                                          @PathVariable("kiosk_number") int kiosk_number,
                                          @PathVariable("user_id") String user_id) {
        return qrService.qrScanning(kiosk_ip, shop_id, kiosk_number, user_id);
    }

    @GetMapping("/test")
    public boolean test() {
        return true;
    }
}
