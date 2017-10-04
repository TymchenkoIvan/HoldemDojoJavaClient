package com.nedogeek.entity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Board {
    List<Card> deskCards;
    int pot;
    String gameRound;
    String dealer;
    String mover;
    List<String> event;
    List<Player> players;
    String cardCombination;

    public List<Card> getDeskCards() {
        return deskCards;
    }

    public int getPot() {
        return pot;
    }

    public String getGameRound() {
        return gameRound;
    }

    public String getDealer() {
        return dealer;
    }

    public String getMover() {
        return mover;
    }

    public List<String> getEvent() {
        return event;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getCardCombination() {
        return cardCombination;
    }

    public void parseMessage(String message) {
        JSONObject json = new JSONObject(message);

        if (json.has("deskPot")) {
            pot = json.getInt("deskPot");
        }
        if (json.has("mover")) {
            mover = json.getString("mover");
        }
        if (json.has("dealer")) {
            dealer = json.getString("dealer");
        }
        if (json.has("gameRound")) {
            gameRound = json.getString("gameRound");
        }
        if (json.has("event")) {
            event = parseEvent(json.getJSONArray("event"));
        }
        if (json.has("players")) {
            players = parsePlayers(json.getJSONArray("players"));
        }

        if (json.has("deskCards")) {
            deskCards = parseCards(((JSONArray) json.get("deskCards")));
        }

        if (json.has("combination")) {
            cardCombination = json.getString("combination");
        }
    }

    private List<String> parseEvent(JSONArray eventJSON) {
        List<String> events = new ArrayList<>();

        for (int i = 0; i < eventJSON.length(); i++) {
            events.add(eventJSON.getString(i));
        }

        return events;
    }

    private List<Player> parsePlayers(JSONArray playersJSON) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < playersJSON.length(); i++) {
            JSONObject playerJSON = (JSONObject) playersJSON.get(i);
            int balance = 0;
            int bet = 0;
            String status = "";
            String name = "";
            List<Card> cards = new ArrayList<>();

            if (playerJSON.has("balance")) {
                balance = playerJSON.getInt("balance");
            }
            if (playerJSON.has("pot")) {
                bet = playerJSON.getInt("pot");
            }
            if (playerJSON.has("status")) {
                status = playerJSON.getString("status");
            }
            if (playerJSON.has("name")) {
                name = playerJSON.getString("name");
            }
            if (playerJSON.has("cards")) {
                cards = parseCards((JSONArray) playerJSON.get("cards"));
            }

            players.add(new Player(name, balance, bet, status, cards));
        }

        return players;
    }

    private List<Card> parseCards(JSONArray cardsJSON) {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < cardsJSON.length(); i++) {
            String cardSuit = ((JSONObject) cardsJSON.get(i)).getString("cardSuit");
            String cardValue = ((JSONObject) cardsJSON.get(i)).getString("cardValue");

            cards.add(new Card(cardSuit, cardValue));
        }

        return cards;
    }

    public double calculateProbability() {
        return 0.0d;
    }
}
