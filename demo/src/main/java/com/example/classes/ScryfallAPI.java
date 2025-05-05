package com.example;
import org.springframework.stereotype.Component;


@Component
public class ScryfallAPI {
    

    public String getCardPrice(String setCode, String cardNumber, Float dolar,Boolean foil) {
        CardPrice caracteristicas = new CardPrice("Test", "Near Mint", "12", (float) 2.70,true);
        CardPriceController obtenerPrecio = new CardPriceController();
        String terminacion = "No Foil";
        if(foil){
            terminacion = "Foil";
        }
        try {
            caracteristicas = obtenerPrecio.getPrice(setCode,cardNumber,dolar,foil);
        
        return "La carta "+ caracteristicas.getName() +" "+ terminacion +" en estado "+ caracteristicas.getState() + " cuesta " + caracteristicas.getPrice() + " c/u\na SCG x "+ dolar +" el total es $" + (dolar*caracteristicas.getPrice()); 
        }
        finally {
            // ... cleanup that will execute whether or not an error occurred ...
        }
    
    
}}

//https://ajax.starcitygames.com/getDiscrepancies/280992