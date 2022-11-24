package com.mufi.mufiServer.service;

import java.util.Map;

public interface PhotoService {
    public Map<String, Object> getPhotoFeed(String id);

    public Map<String, Object> getPaymentPhotos(String payment_id);

    public Map<String, Object> getOriginalPhoto(String payment_id, int photo_number);
}
