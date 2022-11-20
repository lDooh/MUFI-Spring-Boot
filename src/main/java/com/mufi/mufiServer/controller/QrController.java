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
    @Autowired
    private final QrService qrService;

    @GetMapping("/{id}/{kiosk}")
    public Map<String, Object> qrScanning(@PathVariable("id") String id, @PathVariable("kiosk") String kiosk) {
        return qrService.qrScanning(id, kiosk);
    }
}
