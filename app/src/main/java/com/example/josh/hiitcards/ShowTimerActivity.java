package com.example.josh.hiitcards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class ShowTimerActivity extends Activity {

    CardDeck exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_timer);
        //  Create a text view in the layout, findViewById to have variable
        final TextView timeRemaining = (TextView) findViewById(R.id.timeRemaining);
        timeRemaining.setTextSize(40);
        //  Should only show time remaining as "00:00" if there's something wronge below...
        timeRemaining.setText("00:00");
        //setContentView(timeRemaining);

        //  Get the intent to get how long the workout should last
        Intent intent = getIntent();
        int time = intent.getIntExtra(MainActivity.EXTRA_TIME, 0);
        long timeInMilis = (long) time * 60000;

        //  Create a CountDowntimer with the time
            //  On tick, updated the text view showing how much time is remaining
            //  On finish, set text view to 00:00, start activity showing results
        CountDownTimer timer = new CountDownTimer(timeInMilis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minsRemaining = (int) millisUntilFinished / 60000;
                int secsRemaining  = (int) millisUntilFinished / 1000 % 60;
                String time = Integer.toString(minsRemaining) + ":" + String.format("%02d", secsRemaining);
                timeRemaining.setText(time);
            }

            @Override
            public void onFinish() {
                /** Start a new activity showing results, time completed, number of
                 * each exercise completed, etc. */
                timeRemaining.setText("00:00");
             }
        }.start();

        exercise = new CardDeck();

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

    public void nextCard(View view) {

        TextView toDo = (TextView) view;
        String thing;

        Card card = exercise.draw();
        int number = card.getRank();

        if (number == 13) thing = "Do 10 Burpees!!! :D";
        else {

            StringBuilder things = new StringBuilder();
            things.append("Do " + Integer.toString(number+1));
            switch (card.getSuit()) {
                case 0:
                    things.append(" Squat");
                    break;
                case 1:
                    things.append(" High-Knee");
                    break;
                case 2:
                    things.append(" Push-up");
                    break;
                case 3:
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

    }
}
