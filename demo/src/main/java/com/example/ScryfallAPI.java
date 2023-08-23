package com.example;
import org.springframework.stereotype.Component;

@Component
public class ScryfallAPI {
    
    public String getCardPrice(String setCode, String cardNumber, Float dolar) {
        CardPriceController obtenerPrecio = new CardPriceController();
        CardPrice caracteristicas = obtenerPrecio.getPrice(setCode,cardNumber,dolar);
        return "La carta "+ caracteristicas.getName() +" en estado "+ caracteristicas.getState() + " con stock de "+ caracteristicas.getStock() + " cuesta " + caracteristicas.getPrice() + " c/u\na SCG x "+ dolar +" el total es $" + (dolar*caracteristicas.getPrice());} 
                       
    
    
}

//https://ajax.starcitygames.com/getDiscrepancies/280992