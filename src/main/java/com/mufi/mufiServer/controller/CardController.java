package com.mufi.mufiServer.controller;

import com.mufi.mufiServer.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Map;

@RestController
@RequestMapping("app/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/register/{cardNumber}/{exYy}/{exMm}/{birth}/{id}")
    public Map<String, Object> registerCard(@PathVariable("cardNumber") String cardNumber,
                                            @PathVariable("exYy") String exYy, @PathVariable("exMm") String exMm,
                                            @PathVariable("birth") Date birth, @PathVariable("id") String id) {
        return cardService.registerCard(cardNumber, exYy, exMm, birth, id);
    }

    @GetMapping("info/{id}")
    public Map<String, Object> getCards(@PathVariable("id") String id) {
        return cardService.getCards(id);
    }
}
