package com.example.josh.hiitcards;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Josh on 6/25/15.
 */
public class Statistics {


    private HashMap<String, Integer> reps;
    private HashMap<String, Integer> times;
    private ArrayList<Card> cards;

    public Statistics(){
        this.reps = new HashMap<String, Integer>();
        this.times = new HashMap<String, Integer>();
        this.cards = new ArrayList<Card>();
    /*
        this.reps.put("Pushups", 0);
        this.reps.put("Squats", 0);
        this.reps.put("SitUps", 0);
        this.reps.put("HighKnees", 0);
        this.reps.put("Burpees", 0);
        this.times.put("PushTime", 0);
        this.times.put("SquatTime", 0);
        this.times.put("SitTime", 0);
        this.times.put("KneeTime", 0);
        this.times.put("BurpeeTime", 0);
    */

    }


    /*
    TODO: Create a static Hashmap on the MainActivity class so that the user can choose their workouts
        Currently getting a string from the int that represents the workout on the Card object
    */
    public void recordCard(Card card, int time){
        int currentReps = reps.get(card.getSuit());
        reps.put(Integer.toString(card.getSuit()), currentReps+card.getRank());

        int currentTime = times.get(card.getSuit());
        times.put(Integer.toString(card.getSuit()), currentTime + time);

        cards.add(card);
    }

    public int getTime(String exercise){
        return times.get(exercise);
    }

    public int getReps(String exercise){
        return reps.get(exercise);
    }


}
