package com.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScryfallAPIController {

    @Autowired
    private ScryfallAPI scryfallAPI;

    @GetMapping("/scryfall/{setCode}/{cardNumber}")
    public String getCardPrice(@PathVariable String setCode, @PathVariable String cardNumber, @RequestParam Double dolar) {
        return scryfallAPI.getCardPrice(setCode, cardNumber, dolar);
    }
}