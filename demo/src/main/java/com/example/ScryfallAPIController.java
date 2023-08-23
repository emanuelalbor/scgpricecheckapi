package com.example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScryfallAPIController {

    private ScryfallAPI scryfallAPI;

    @GetMapping("/scryfall/{setCode}/{cardNumber}/{dolar}")
    public String getCardPrice(@PathVariable String setCode, @PathVariable String cardNumber, @PathVariable Float dolar) {
        return scryfallAPI.getCardPrice(setCode, cardNumber, dolar);
    }
}