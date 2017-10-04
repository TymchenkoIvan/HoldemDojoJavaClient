package com.nedogeek.entity;

public class Card {
    final String suit;
    final String value;

    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Card: " + value + " " + suit;
    }
}