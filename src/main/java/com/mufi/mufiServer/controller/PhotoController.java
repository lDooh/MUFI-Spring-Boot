package com.mufi.mufiServer.controller;

import com.mufi.mufiServer.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("app/photo")
@RequiredArgsConstructor
public class PhotoController {
    @Autowired
    private final PhotoService photoService;

    @GetMapping("feed/{id}")
    public Map<String, Object> getPhotoFeed(@PathVariable("id") String id) {
        return photoService.getPhotoFeed(id);
    }
}
