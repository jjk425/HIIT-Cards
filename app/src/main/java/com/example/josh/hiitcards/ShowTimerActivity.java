package com.example.josh.hiitcards;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ShowTimerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //  Create a text view in the layout, findViewById to have variable
        final TextView timeRemaining = new TextView(this);
        timeRemaining.setTextSize(40);
        timeRemaining.setText("00:00");
        setContentView(timeRemaining);

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
                String time = Integer.toString(minsRemaining) + ":" + Integer.toString(secsRemaining);
                timeRemaining.setText(time);
            }

            @Override
            public void onFinish() {
                /** Start a new activity showing results, time completed, number of
                 * each exercise completed, etc. */
                timeRemaining.setText("00:00");
             }
        }.start();

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
}
