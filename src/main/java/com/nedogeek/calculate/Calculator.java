package com.nedogeek.calculate;

import com.nedogeek.entity.Card;
import com.nedogeek.entity.CardValue;

import java.util.List;

import static com.nedogeek.calculate.Constants.*;

public class Calculator {

    private int getCardValuesSum(List<Card> cards){
        CardValue cardOne = CardValue.getByValue(cards.get(0).getValue());
        CardValue cardTwo = CardValue.getByValue(cards.get(1).getValue());

        return cardOne.getCoeff() + cardTwo.getCoeff();
    }

    private boolean isPair(List<Card> cards){
        return cards.get(0).getValue().equals(cards.get(1).getValue());
    }

    private boolean isOneSuit(List<Card> cards) {
        return cards.get(0).getSuit().equals(cards.get(1).getSuit());
    }

    private boolean isStraightPossible(List<Card> cards) {
        CardValue cardOne = CardValue.getByValue(cards.get(0).getValue());
        CardValue cardTwo = CardValue.getByValue(cards.get(1).getValue());

        return Math.abs(cardOne.getCoeff() - cardTwo.getCoeff()) <= 3;
    }

    public double onHandCardCoefficient(List<Card> cards){
        cards.forEach(System.out::println);

        double coeff = getCardValuesSum(cards);
        if(isPair(cards)){
            System.out.println("cards are PAIR_COEFF");
            coeff *= PAIR_COEFF;
        }

        if(isOneSuit(cards)){
            System.out.println("cards are ONE_SUIT_COEFF");
            coeff *= ONE_SUIT_COEFF;
        }

        if(isStraightPossible(cards)){
            System.out.println("cards are POSSIBLE_STRAIGHT_COEFF");
            coeff *= POSSIBLE_STRAIGHT_COEFF;
        }
        return coeff;
    }
}
