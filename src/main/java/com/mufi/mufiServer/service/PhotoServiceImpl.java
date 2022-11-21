package com.mufi.mufiServer.service;

import com.mufi.mufiServer.dao.PaymentMapper;
import com.mufi.mufiServer.dao.PhotoMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    private final PhotoMapper photoMapper;

    @Autowired
    private final PaymentMapper paymentMapper;

    @Override
    public Map<String, Object> getPhotoFeed(String id) {
        Map<String, Object> map = new HashMap<>();

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }


}
