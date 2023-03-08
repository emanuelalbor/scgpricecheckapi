package com.example;

public class CardPrice {
    private String name;
    private String state;
    private String stock;
    private Double price;

    public CardPrice(String name, String state, String stock, Double price) {
        this.name = name;
        this.state = state;
        this.stock = stock;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }
}