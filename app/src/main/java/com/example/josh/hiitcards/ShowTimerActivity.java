package com.example.josh.hiitcards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ShowTimerActivity extends Activity {

    // public final static String EXTRA_STATS = "com.example.josh.hiitcards.STATS";

    private CardDeck exercise;
    private CountDownTimer timer;
    private long timeInMilis;
    private boolean timerRunning = false;
    TextView timeRemaining;
    TextView cardExercise;
    Button startPauseButton;
    Button finishButton;
    Boolean init = false;
    public static Statistics stats;
    private Card currentCard;

    private long repStartTime;
    private long repFinishTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Set Content view
        setContentView(R.layout.activity_show_timer);

        //  Set the variable to access the Time Remaining TextView
        timeRemaining = (TextView) findViewById(R.id.timeRemaining);

        //  Declare final variables for the StartPause and Finish buttons
        final Button strtPauseButton = (Button) findViewById(R.id.startPauseButton);
        final Button fnishButton = (Button) findViewById(R.id.finishButton);
        //  Set the instance variables to reference the final variables declared above
        startPauseButton = strtPauseButton;
        finishButton = fnishButton;

        cardExercise = (TextView) findViewById(R.id.Exercise);


        //  Get the intent to get how long the workout should last
        Intent intent = getIntent();
        int time = intent.getIntExtra(MainActivity.EXTRA_TIME, 0);

        //  Convert the time into milliseconds and store in instance variable
        this.timeInMilis = (long) time * 60000;


        //  Initialize the text in the timeRemaining TextView by calling myTick with the time in Milis,
        //  as received from the Intent
        timeRemaining.setTextSize(40);
        myTick(this.timeInMilis);

        this.stats = new Statistics();


        /**
         *  Create a CountDowntimer with the time
         *  Override the onTick and onFinish methods to call
         *  myTick and myFinish respectively, defined below */

        this.timer = new CountDownTimer(timeInMilis+1000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {
                myTick(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                myFinish();
             }
        };

        //  Initialize the CardDeck instance variable
        exercise = new CardDeck();

    }

    public void myTick(long mils) {
        timeInMilis = mils;
        if (mils % 1000 == 0) {
            int minsRemaining = (int) mils / 60000;
            int secsRemaining = (int) mils / 1000 % 60;
            String time = Integer.toString(minsRemaining) + ":" + String.format("%02d", secsRemaining);
            timeRemaining.setText(time);
        }

    }

    public void myFinish(){
        // Start a new activity showing results, time completed, number of
        // each exercise completed, etc.

        timeRemaining.setText("00:00");
        this.timeInMilis = 0;
        finishWorkout(timeRemaining);
    }

/**
     * Not needed for now
     *  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_timer, menu);
        return true;
    }
 */



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * TODO: Pull the part of this function that records the card into a separate function, that is only called if the exercise isn't skipped.
     * TODO: Create a 'Skip' button that calls this function with a null argument.
     * @param view
     * @return
     */
    public Card nextCard(View view) {

        long repTime = 0;

        //  initiate the rep start time when you first start the timer
        if (!init){
            repStartTime = timeInMilis;
        }
        else {
            repFinishTime = timeInMilis;
            repTime = repStartTime - repFinishTime;

            // reset the rep start time
            repStartTime = timeInMilis;

            //  log the card that was just completed
            //System.out.println(currentCard.toString());
            //System.out.println(Long.toString(repTime));
            System.out.println("Current card: " + currentCard.toString());
            stats.recordCard(currentCard, repTime);
        }



        TextView toDo = (TextView) view;
        String thing;

        Card card = exercise.draw();
        int number = card.getRank();

        if (number == 13) thing = "Do 10 Burpees!!! :D";
        else {

            StringBuilder things = new StringBuilder();
            things.append("Do " + Integer.toString(number+1));
            switch (card.getSuit()) {
                case "hearts":
                    things.append(" Squat");
                    break;
                case "diamonds":
                    things.append(" High-Knee");
                    break;
                case "spades":
                    things.append(" Push-up");
                    break;
                case "clubs":
                    things.append(" Sit-up");
                    break;
                default:
                    thing = " Something's wrong...";
                    break;
            }

            if (number == 0) things.append("!");
            else things.append("s!");
            thing = things.toString();
        }
        toDo.setText(thing);
        currentCard = card;
        return card;

    }

    public void pauseResume(View view){
        if (timerRunning == true) {
            timerRunning = false;
            timer.cancel();
            startPauseButton.setText("Resume");
            finishButton.setVisibility(View.VISIBLE);
            finishButton.setClickable(true);
            cardExercise.setClickable(false);
        }
        else {
            timer = new CountDownTimer(timeInMilis, 1) {
                @Override
                public void onTick(long millisUntilFinished) {
                    myTick(millisUntilFinished);
                }

                @Override
                public void onFinish() {
                    myFinish();
                }
            }.start();
            if (!init) {
                nextCard(cardExercise);
                init = true;
            }
            timerRunning = true;
            cardExercise.setClickable(true);
            startPauseButton.setText("Pause");

        }

    }

    public void finishWorkout(View view){
        //  Below will create intent to show results in new activity, once I implement that class
        Intent results = new Intent(this, ShowResultsActivity.class);
       // results.putExtra(EXTRA_STATS, );
        startActivity(results);
    }
}
