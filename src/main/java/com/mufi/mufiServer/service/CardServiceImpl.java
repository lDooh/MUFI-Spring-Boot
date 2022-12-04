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
        String cardId = cardNumber;

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
}
