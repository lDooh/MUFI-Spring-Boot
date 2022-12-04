package com.mufi.mufiServer.service;

import com.mufi.mufiServer.dao.CardMapper;
import com.mufi.mufiServer.dto.AppCardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardMapper cardMapper;

    @Override
    public Map<String, Object> registerCard(String cardNumber, String exYy, String exMm, Date birth, String id) {
        Map<String, Object> map = new HashMap<>();

        map.put("isRegistrationSuccess", 0);

        /* 카드 중복 -1
        토스페이먼츠 등록 실패 -2
        그 외 -3
        * */

        // cardId 랜덤 생성 (생략, 카드 번호와 동일하게 생성)
        String cardId = randomCardNumber();

        // 카드 유효기간을 yyyy-mm-dd 형식으로 저장
        Date expDate = Date.valueOf("20" + exYy + "-" + exMm + "-01");

        try {
            cardMapper.registerCard(cardId, id, cardNumber, expDate);

            map.replace("isRegistrationSuccess", 1);
        } catch (Exception e) {
            map.put("Failed Type", -3);
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public Map<String, Object> getCards(String id) {
        Map<String, Object> map = new HashMap<>();

        map.put("isGetCardsSuccess", 0);

        try {
            ArrayList<AppCardDto> arrayList = cardMapper.getCards(id);
            map.put("cards", arrayList);

            map.replace("isGetCardsSuccess", 1);
        } catch (Exception e) {
            map.put("Failed Type", -2);
            e.printStackTrace();
        }

        return map;
    }

    @Override
    public Map<String, Object> deleteCard(String card_id) {
        Map<String, Object> map = new HashMap<>();

        map.put("isDeleteCardSuccess", 0);

        try {
            boolean isDeleteCardSuccess = cardMapper.deleteCard(card_id);

            map.replace("isDeleteCardSuccess", 1);
        } catch (Exception e) {
            map.put("Failed Type", -2);
            e.printStackTrace();
        }

        return map;
    }

    // https://digiconfactory.tistory.com/entry/%EC%9E%90%EB%B0%94-Random-%ED%81%B4%EB%9E%98%EC%8A%A4%EB%A1%9C-%EB%AC%B4%EC%9E%91%EC%9C%84-%EB%AC%B8%EC%9E%90%EC%97%B4-%EB%A7%8C%EB%93%A4%EA%B8%B0-Random-Class
    private String randomCardNumber() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 16; i++) {
            int choice = random.nextInt(3);
            switch (choice) {
                case 0:
                    sb.append((char) ((int) random.nextInt(25) + 97));
                    break;
                case 1:
                    sb.append((char) ((int) random.nextInt(25) + 65));
                    break;
                case 2:
                    sb.append((char) ((int) random.nextInt(10) + 48));
                    break;
                default:
                    break;
            }
        }
        return sb.toString();
    }
}
