package com.mufi.mufiServer.service;

import java.util.Map;

public interface PhotoService {
    public Map<String, Object> getPhotoFeed(String id);

    public Map<String, Object> getPaymentPhotos(String payment_id);
}
