package com.nedogeek.entity;

import java.util.List;

public class Player {

    final String name;
    final int balance;
    final int bet;
    final String status;
    final List<Card> cards;

    Player(String name, int balance, int bet, String status, List<Card> cards) {
        this.name = name;
        this.balance = balance;
        this.bet = bet;
        this.status = status;
        this.cards = cards;
    }
}