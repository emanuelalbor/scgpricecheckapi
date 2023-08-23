package com.example;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import org.jsoup.*; 
import org.jsoup.nodes.*; 
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class CardPriceController {

    //@Autowired
   // private CardPrice cardPrice;

    @GetMapping("/price")
    public CardPrice getPrice(@RequestParam String setCode, @RequestParam String cardNumber, @RequestParam Float dolar) {
        try {
            // Create a URL for the Scryfall API request
            String url = "https://api.scryfall.com/cards/" + setCode + "/" + cardNumber;
            JSONObject json = readJsonFromUrl(url);

            // Get the name of the card and split it by capital letters
            String cardName = json.getString("name");
            cardName = cardName.replaceAll(",", "-");
            cardName = cardName.replaceAll("([a-z])([A-Z])", "$1-$2").toLowerCase();
            String nombreCarta = cardName;
            nombreCarta = nombreCarta.replaceAll("-", " ");

            // Create the Star City Games URL
            String scgUrl = "https://starcitygames.com/" + cardName + "-sgl-mtg-" + setCode + "-" + cardNumber + "-enn/";
            Document doc = Jsoup.connect(scgUrl)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36") 
                .header("Accept-Language", "*") 
                .get();
            String precio = doc.selectFirst("span.price.price--withoutTax").text();
            String estado = doc.selectFirst("span.form-option-variant").text();
            String stock = doc.select("span.stock-level").text();
            precio = precio.replace("$", "").replace(",", ".");
            Float precioDouble = Float.valueOf(precio);
            return new CardPrice(nombreCarta, estado,stock,precioDouble);
        }
    catch (IOException e) {
        System.out.println("Error reading URL: " + e.getMessage());
    } catch (JSONException e) {
        System.out.println("Error parsing JSON: " + e.getMessage());
    }
        return null;
    }
    private static String readUrl(String urlString) throws IOException {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(new URL(urlString).openStream());
        while (scanner.hasNext()) {
            sb.append(scanner.next());
            scanner.close();
        }
        return sb.toString();
    }

    private static JSONObject readJsonFromUrl(String urlString) throws IOException, JSONException {
        String jsonText = readUrl(urlString);
        return new JSONObject(jsonText);
    }
    
    
}
