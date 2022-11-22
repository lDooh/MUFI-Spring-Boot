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
        try {
            ArrayList<PaymentDto> paymentDtoArrayList = paymentMapper.getPhotoFeed(id);
            Iterator<PaymentDto> itr = paymentDtoArrayList.iterator();

            ArrayList<PhotoInfo> photoInfoArrayList = new ArrayList<>();

            while (itr.hasNext()) {
                PaymentDto paymentDto = itr.next();
                // TODO
                photoInfoArrayList.add(new PhotoInfo(null, null, null, null));
            }
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
