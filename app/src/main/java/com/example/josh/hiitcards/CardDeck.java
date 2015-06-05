package com.example.josh.hiitcards;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Josh on 6/1/15.
 * This class is the data structure I built to represent a deck of cards using an ArrayList.
 * The deck should contain 54 cards (2 jokers, in addition to the standard cards), and should be shuffled
 * so that cards are returned in a random order when drawn
 */
public class CardDeck {

    private ArrayList<Card> deck;

    public CardDeck(){

        //  The deck is an ArrayList of cards
        deck = new ArrayList<Card>();

        //  Add all of the cards to the deck
        populate(deck);

        //  Shuffle the deck
        deck = shuffle(deck);

    }

    /**
     * This method adds cards to the deck
     * @param deck the deck you want to populate
     */
    public void populate(ArrayList<Card> deck){
        //  Add all of the cards Ace through King
        for (int suit = 0; suit < 4; suit++){
            for (int rank = 0; rank < 13; rank ++){
                deck.add(new Card(suit, rank));
            }
        }
        //  Add jokers (for Burpees!!)
        deck.add(new Card(0, 13));
        deck.add(new Card(1, 13));
    }

    /**
     *
     * @param deck the ArrayList of cards you want to shuffle
     * @return a shuffled ArrayList
     */
    public ArrayList<Card> shuffle(ArrayList<Card> deck){

        //  init random generator and cards to rotate
        Random rand = new Random();
        int card1, card2, card3;

        //  To shuffle, randomly find 3 cards and rotate their positions, 100 times
        for (int i = 0; i < 100; i++) {
            //  Get the indices of 3 cards
            card1 = rand.nextInt(deck.size() - 1);
            card2 = rand.nextInt(deck.size() - 1);
            card3 = rand.nextInt(deck.size() - 1);

            //  Rotate the three cards
            Card temp = deck.get(card1);
            deck.set(card1, deck.get(card2));
            deck.set(card2, deck.get(card3));
            deck.set(card3, temp);
        }
        return deck;
    }

    /**
     * Draw a card from the deck by removing it from the front of the ArrayList
     * If the deck is empty after this card is drawn, repopulate and shuffle the deck
     * @return a Card that is removed from the front of the Deck
     */
    public Card draw(){
        Card drawn = deck.remove(0);
        if (deck.size() == 0){
            populate(deck);
            shuffle(deck);
        }
        return drawn;
    }


}
