package com.example.josh.hiitcards;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Josh on 6/25/15.
 */
public class Statistics {


    private HashMap<String, Integer> reps;
    private HashMap<String, Long> times;

    //  TODO: Create a new class that has both the Card and the time for each card. The ArrayList should be of the new class Type
    private ArrayList<Card> cards;

    public Statistics(){
        this.reps = new HashMap<String, Integer>();
        this.times = new HashMap<String, Long>();
        this.cards = new ArrayList<Card>();

        for (String suit : Card.suits){
            reps.put(suit, 0);
            times.put(suit, (long) 0);
        }


    }


    /*
    TODO: Create a public final static array on the MainActivity class so that the user can choose their workouts
        Currently getting a string from the int that represents the workout on the Card object
    */
    public void recordCard(Card card, long time){
        int currentReps = reps.get(card.getSuit());
        reps.put(card.getSuit(), currentReps+card.getRank());

        long currentTime = times.get(card.getSuit());
        times.put(card.getSuit(), currentTime + time);

        cards.add(card);
    }

    public long getTime(String exercise){
        return times.get(exercise);
    }

    public int getReps(String exercise){
        return reps.get(exercise);
    }


    @Override
    public String toString(){
        StringBuilder cstring = new StringBuilder();
        for (Card c : cards){
            //  TODO: convert this into the right string using the array of workouts that is to be built
            cstring.append(c.toString()+'\n');
        }
        return cstring.toString();
    }

}
