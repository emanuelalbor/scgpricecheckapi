package com.example;

public class CardPrice {
    private String name;
    private String state;
    private String stock;
    private Float price;
    private Boolean foil;
    private Float pricePeso;


    public CardPrice(String name, String state, String stock, Float price,Boolean foil) {
        this.name = name;
        this.state = state;
        this.stock = stock;
        this.price = price;
        this.foil = foil;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public String getStock() {
        return stock;
    }

    public Float getPrice() {
        return price;
    }
    public Boolean isFoil() {
        return foil;
    }
    public Float getPricePeso(float dolar) {
        pricePeso = this.getPrice() * dolar;
        return pricePeso;
    }
}