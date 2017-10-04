package com.nedogeek.entity;

import com.nedogeek.Calculator;
import com.nedogeek.Credentials;
import com.nedogeek.Parser;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;


public class Board {

    private Parser parser = new Parser();
    private Calculator calculator = new Calculator();

    List<Card> deskCards;
    int pot;
    String gameRound;
    String dealer;
    String mover;
    List<String> event;
    List<Player> players;
    String cardCombination;
    Player myPlayer;

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
            event = parser.parseEvent(json.getJSONArray("event"));
        }
        if (json.has("players")) {
            players = parser.parsePlayers(json.getJSONArray("players"));
        }

        if (json.has("deskCards")) {
            deskCards = parser.parseCards(((JSONArray) json.get("deskCards")));
        }

        if (json.has("combination")) {
            cardCombination = json.getString("combination");
        }
    }

    public double calculateProbability() {
        calculator.onHandCardCoefficient(myPlayer.cards);
        return 0.0d;
    }

    public void setMyPlayer(){
        for (Player p: players)
            if (p.name.equals(Credentials.USER_NAME))
                myPlayer = p;
    }
}
