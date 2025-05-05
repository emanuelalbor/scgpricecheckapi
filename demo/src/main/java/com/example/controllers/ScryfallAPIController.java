package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScryfallAPIController {

    private final ScryfallAPI scryfallAPI;

    public ScryfallAPIController(ScryfallAPI scryfallAPI) {
        this.scryfallAPI = scryfallAPI;}

    @GetMapping("/scryfall/{setCode}/{cardNumber}/{dolar}/{foil}")
    public String getCardPrice(@PathVariable String setCode, @PathVariable String cardNumber, @PathVariable Float dolar,@PathVariable Boolean foil) {
        return scryfallAPI.getCardPrice(setCode, cardNumber, dolar, foil);
    }
}

