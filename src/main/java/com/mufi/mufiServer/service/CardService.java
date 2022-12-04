package com.mufi.mufiServer.service;

import java.sql.Date;
import java.util.Map;

public interface CardService {
    public Map<String, Object> registerCard(String cardNumber, String exYy, String exMm, Date birth, String id);

    public Map<String, Object> getCards(String id);

    public Map<String, Object> deleteCard(String card_id);
}
