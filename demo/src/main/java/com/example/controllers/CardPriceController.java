package com.example.controllers;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.jsoup.*; 
import org.jsoup.nodes.*; 
import org.json.JSONException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.example.classes.CardPrice;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class CardPriceController {

    private static String readTextFromUrl(String urlString) throws IOException {
        StringBuilder response = new StringBuilder();
        URL url = new URL(urlString);
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        }
        
        return response.toString();
    }
    private String extractCardName(String cardInfo) {
        // Implement your logic to extract the card name from the text response
        // Example: Assuming the card name is everything before the first {
            int firstBraceIndex = cardInfo.indexOf('{');

        if (firstBraceIndex != -1) {
            if (cardInfo.contains("/n")){
                return cardInfo.split("\n")[0];
            }else{
            return cardInfo.substring(0, firstBraceIndex).trim();}
        } else {
            // Handle the case where { is not found, or return the entire string if needed
            return cardInfo.trim();
        }
    
    }
     private String buildSCGURL(String nombreCarta,String setCode,String cardNumber,Boolean foil){
        String finish;
        if (foil){
            finish = "f";
        }
        else{
            finish = "n";
        }
        String scgUrl = "https://starcitygames.com/" + nombreCarta + "-sgl-mtg-" + setCode + "-" + cardNumber + "-en" + finish + "/";
        return scgUrl;
     }

    @GetMapping("/price")
    public CardPrice getPrice(@RequestParam String setCode, @RequestParam String cardNumber, @RequestParam Float dolar,@RequestParam Boolean foil) {
    
        try {
            String cardNumberScg = String.format("%03d", Integer.parseInt(cardNumber));
            String apiUrl = "https://api.scryfall.com/cards/" + setCode + "/" + cardNumber + "/en?format=text";
            String cardInfo = readTextFromUrl(apiUrl);
            String cardName = extractCardName(cardInfo);
            String nombreCarta = cardName;
            nombreCarta = nombreCarta.replaceAll("[,\\s]+", "-");
            nombreCarta = nombreCarta.replaceAll(" ", "-");
            nombreCarta = nombreCarta.replaceAll(",", "-");
            nombreCarta = nombreCarta.replaceAll("'", "");

            // Create the Star City Games URL
            String starcity= buildSCGURL(nombreCarta,setCode,cardNumberScg,foil);
            Document doc = Jsoup.connect(starcity)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36") 
                .header("Accept-Language", "*") 
                .get();
            String precio = doc.selectFirst("span.price.price--withoutTax").text();
            String estado = doc.selectFirst("span.form-option-variant").text();
            String stock = doc.attr("stock-level").toString();
            precio = precio.replace("$", "").replace(",", ".");
            Float precioDouble = Float.valueOf(precio);
            return new CardPrice(cardName,estado,stock,precioDouble,foil);
        }
    catch (IOException e) {
        System.out.println("Error reading URL: " + e.getMessage());
    } catch (JSONException e) {
        System.out.println("Error parsing JSON: " + e.getMessage());
    }
        return null;
    }
    //@Override
    public CardPrice getCardPrice(String setCode, String cardNumber, Float dolar,Boolean foil) {
        return this.getPrice(cardNumber, cardNumber,dolar,foil);
    }
 
}
