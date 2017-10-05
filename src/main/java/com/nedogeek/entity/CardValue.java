package com.nedogeek.entity;

/**
 * User: Konstantin Demishev
 * Date: 22.11.12
 * Time: 13:58
 */
public enum CardValue {
    TWO ("2", "2", 6),
    THREE ("3", "3", 7),
    FOUR ("4", "4", 8),
    FIVE ("5", "5", 9),
    SIX ("6", "6", 10),
    SEVEN ("7", "7", 11),
    EIGHT ("8", "8", 12),
    NINE ("9", "9", 13),
    TEN ("10", "10", 14),
    JACK ("J", "Jack", 15),
    QUEEN ("Q", "Queen", 16),
    KING ("K", "King", 17),
    ACE ("A", "Ace", 18);

    private final String stringValue;
    private final String fullName;
    private final int coeff;

    CardValue(String stringValue, String fullName, int coeff) {
        this.stringValue = stringValue;
        this.fullName = fullName;
        this.coeff = coeff;
    }

    @Override
    public String toString() {
        return stringValue;
    }

    public String getFullName() {
        return fullName;
    }

    public int getCoeff(){
        return coeff;
    }

    public static CardValue getByValue(String value){
        for(CardValue cardValue: CardValue.values())
            if (cardValue.toString().equals(value))
                return cardValue;

        return CardValue.TWO;
    }
}
