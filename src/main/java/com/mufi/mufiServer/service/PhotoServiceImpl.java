package com.mufi.mufiServer.service;

import com.mufi.mufiServer.dao.PaymentMapper;
import com.mufi.mufiServer.dao.PhotoMapper;
import com.mufi.mufiServer.dto.PaymentDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

        /* 사진 피드 전달 내용
        * payment_id, payment_date, photo_number(1), image_content
        * */
        // TODO: PhotoInfo 전달하도록 구현, base64 전달
        // TODO: 썸네일 전달

        try {
//            ArrayList<PaymentDto> paymentDtoArrayList = paymentMapper.getPhotoFeed(id);
//            Iterator<PaymentDto> itr = paymentDtoArrayList.iterator();
//
//            ArrayList<PhotoInfo> photoInfoArrayList = new ArrayList<>();
//
//            while (itr.hasNext()) {
//                PaymentDto paymentDto = itr.next();
//                // TODO
//                photoInfoArrayList.add(new PhotoInfo(null, null, null, null));
//            }
//            map.put("payments", paymentDtoArrayList);
            map.put("payments", paymentMapper.getPhotoFeed(id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public Map<String, Object> getPaymentPhotos(String payment_id) {
        Map<String, Object> map = new HashMap<>();

        try {
            // TODO: PhotoInfo 전달하도록 구현, base64 전달
            // TODO: 썸네일 전달
            map.put("photos", paymentMapper.getPaymentPhotos(payment_id));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    @Getter
    class PhotoInfo {
        private String payment_id;
        private Date payment_date;
        private int photo_number;
        private String image_content;

        public PhotoInfo(String payment_id, Date payment_date, int photo_number, String image_content) {
            this.payment_id = payment_id;
            this.payment_date = payment_date;
            this.photo_number = photo_number;
            this.image_content = image_content;
        }
    }
}
