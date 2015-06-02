package com.example.josh.hiitcards;

/**
 * Created by Josh on 6/1/15.
 */
public class Card {
    private int suit, rank;

    private static String[] suits = {"hearts", "diamonds", "spades", "clubs"};

    public Card(int suit, int rank){
        //  should add condition to validate the rank/suit...

        //  initializes the card with the suit and rank
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return Integer.toString(rank) + " of " + suits[suit];
    }

    public int getRank(){
        return rank;
    }

    public int getSuit(){
        return suit;
    }
}
