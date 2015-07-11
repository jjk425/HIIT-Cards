package com.example.josh.hiitcards;

/**
 * Created by Josh on 6/1/15.
 */
public class Card {
    private int rank;
    private String suit;

    public static String[] suits = {"hearts", "diamonds", "spades", "clubs"};

    public Card(int suit, int rank){
        //  should add condition to validate the rank/suit...

        //  initializes the card with the suit and rank
        this.suit = suits[suit];
        this.rank = rank;
    }

    @Override
    public String toString() {
        return (Integer.toString(rank+1) + " of " + suit);
    }

    public int getRank(){
        return rank;
    }

    public String getSuit(){
        return suit;
    }
}
