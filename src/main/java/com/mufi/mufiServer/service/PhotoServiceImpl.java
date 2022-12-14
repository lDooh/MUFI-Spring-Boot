package com.mufi.mufiServer.service;

import com.mufi.mufiServer.dao.PaymentMapper;
import com.mufi.mufiServer.dao.PhotoMapper;
import com.mufi.mufiServer.dao.ShopMapper;
import com.mufi.mufiServer.dto.PaymentDto;
import com.mufi.mufiServer.dto.PhotosDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final PhotoMapper photoMapper;

    private final PaymentMapper paymentMapper;

    private final ShopMapper shopMapper;

    @Override
    public Map<String, Object> getPhotoFeed(String id) {
        Map<String, Object> map = new HashMap<>();

        try {
            ArrayList<PaymentDto> paymentDtoArrayList = paymentMapper.getPhotoFeed(id);     // 1결제-1사진(ArrayList)
            Iterator<PaymentDto> itr = paymentDtoArrayList.iterator();

            // 클라이언트에게 전달될 데이터
            ArrayList<PhotoInfo> photoInfoArrayList = new ArrayList<>();

            while (itr.hasNext()) {
                PaymentDto paymentDto = itr.next();

                photoInfoArrayList.add(new PhotoInfo(paymentDto.getPayment_id(),
                        paymentDto.getPayment_date(),
                        paymentDto.getPhotosDtoArrayList().get(0).getPhoto_number(),
                        imageToThumbnailByte(paymentDto.getPhotosDtoArrayList().get(0).getImage_path())));
            }
            map.put("photo", photoInfoArrayList);
        } catch (Exception e) {
            map.put("Failed Type", -2);
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public Map<String, Object> getPaymentPhotos(String payment_id) {
        Map<String, Object> map = new HashMap<>();

        try {
            PaymentDto paymentDto = paymentMapper.getPaymentPhotos(payment_id);
            ArrayList<PhotosDto> photosDtoArrayList = paymentDto.getPhotosDtoArrayList();

            // 클라이언트에게 전달될 데이터
            ArrayList<PhotoInfo> photoInfoArrayList = new ArrayList<>();

            for (PhotosDto photosDto : photosDtoArrayList) {
                photoInfoArrayList.add(new PhotoInfo(
                        paymentDto.getPayment_id(),
                        paymentDto.getPayment_date(),
                        photosDto.getPhoto_number(),
                        imageToThumbnailByte(photosDto.getImage_path())
                ));
            }
            map.put("photo", photoInfoArrayList);
        } catch (Exception e) {
            map.put("Failed Type", -2);
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public Map<String, Object> getOriginalPhoto(String payment_id, int photo_number) {
        Map<String, Object> map = new HashMap<>();

        try {
            PhotosDto photosDto = photoMapper.getOriginalPhoto(payment_id, photo_number);
            PaymentDto paymentDto = paymentMapper.getPayment(payment_id);
            String shopName = shopMapper.getShopInfo(paymentDto.getShop_id()).getShop_name();

            PhotoInfo photoInfo = new PhotoInfo(
                    photosDto.getPayment_id(),
                    paymentDto.getPayment_date(),
                    photosDto.getPhoto_number(),
                    imageToByte(photosDto.getImage_path())
            );

            ArrayList<PhotoInfo> photoInfoArrayList = new ArrayList<>();
            photoInfoArrayList.add(photoInfo);

            map.put("photo", photoInfoArrayList);
            map.put("photo_size", getImageMB(photosDto.getImage_path()));
            map.put("shop_name", shopName);
        } catch (Exception e) {
            map.put("Failed Type", -2);
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public Map<String, Object> deletePhoto(String payment_id, int photo_number) {
        Map<String, Object> map = new HashMap<>();
        map.put("isDeletePhotoSuccess", 0);

        try {
            int isDeletePhotoSuccess = photoMapper.deletePhoto(payment_id, photo_number);

            if (isDeletePhotoSuccess > 0) { // 사진 삭제 성공
                map.replace("isDeletePhotoSuccess", 1);
            } else {
                map.put("Failed Type", -1);
            }
        } catch (Exception e) {
            map.put("Failed Type", -2);
            e.printStackTrace();
        }

        return map;
    }

    // 쿼리 결과(filePath)에서 이미지 경로를 읽고 Base64 String으로 인코딩
    private String imageToByte(String filePath) throws IOException {
        byte[] imageContentByte = FileUtils.readFileToByteArray(new File(filePath));

        return Base64.getEncoder().encodeToString(imageContentByte);
    }

    // 썸네일 생성 메소드
    private String imageToThumbnailByte(String filepath) throws IOException {
        BufferedImage bi = ImageIO.read(new File(filepath));
        BufferedImage thumbnail = Scalr.resize(bi, 450);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ImageIO.write(thumbnail, "png", bos);
        byte[] imageBytes = bos.toByteArray();

        return new String(Base64.getEncoder().encode(imageBytes));
    }

    // 파일 용량 반환
    private double getImageMB(String filepath) throws IOException {
        Path path = Paths.get(filepath);

        long bytes = Files.size(path);

        return ((double)bytes / 1024 / 1024);
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
