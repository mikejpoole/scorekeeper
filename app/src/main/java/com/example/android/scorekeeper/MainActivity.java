package com.example.android.scorekeeper;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Integer iRace = 1;

    private boolean bWinnerBoat1;
    private boolean bWinnerBoat2;
    private boolean bScoreAwarded;

    private boolean bEndedBoat1;
    private boolean bEndedBoat2;

    private long timeWhenStopped = 0;
    private boolean stopClicked;

    private Chronometer myChronometer;
    private Button buttonStart;
    private Button buttonReset;

    private Button buttonFinish1;
    private Button buttonDns1;
    private Button buttonDnf1;
    private Button buttonDisq1;
    private Integer iScoreBoat1 = 0;
    private TextView scoreBoat1;
    private String sResultsBoat1 = "";
    private TextView resultsBoat1;

    private Button buttonFinish2;
    private Button buttonDns2;
    private Button buttonDnf2;
    private Button buttonDisq2;
    private Integer iScoreBoat2 = 0;
    private TextView scoreBoat2;
    private String sResultsBoat2 = "";
    private TextView resultsBoat2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myChronometer = findViewById(R.id.chronometer);

        buttonStart = findViewById(R.id.buttonStart);
        buttonStart.setText("Start Race " + iRace.toString());

        buttonReset = findViewById(R.id.buttonReset);

        scoreBoat1 = findViewById(R.id.score1);
        buttonFinish1 = findViewById(R.id.buttonFinish1);
        buttonDns1 = findViewById(R.id.buttonDns1);
        buttonDnf1 = findViewById(R.id.buttonDnf1);
        buttonDisq1 = findViewById(R.id.buttonDisq1);

        buttonFinish2 = findViewById(R.id.buttonFinish2);
        buttonDns2 = findViewById(R.id.buttonDns2);
        buttonDnf2 = findViewById(R.id.buttonDnf2);
        buttonDisq2 = findViewById(R.id.buttonDisq2);

        myChronometer.setVisibility(View.GONE);

        buttonFinish1.setVisibility(View.GONE);
        buttonDns1.setVisibility(View.GONE);
        buttonDnf1.setVisibility(View.GONE);
        buttonDisq1.setVisibility(View.GONE);
        scoreBoat1 = findViewById(R.id.score1);
        resultsBoat1 = findViewById(R.id.resultsBoat1);

        buttonFinish2.setVisibility(View.GONE);
        buttonDns2.setVisibility(View.GONE);
        buttonDnf2.setVisibility(View.GONE);
        buttonDisq2.setVisibility(View.GONE);
        scoreBoat2 = findViewById(R.id.score2);
        resultsBoat2 = findViewById(R.id.resultsBoat2);
    }


    public void startRace(View view){
        Toast.makeText(getApplicationContext(),"Starting race " + iRace.toString(),Toast.LENGTH_LONG).show();

        myChronometer.setVisibility(View.VISIBLE);
        myChronometer.setBase(SystemClock.elapsedRealtime());
        myChronometer.start();

        buttonStart.setVisibility(View.GONE);
        buttonReset.setVisibility(View.VISIBLE);

        buttonFinish1.setVisibility(View.VISIBLE);
        buttonDns1.setVisibility(View.VISIBLE);
        buttonDnf1.setVisibility(View.VISIBLE);
        buttonDisq1.setVisibility(View.VISIBLE);

        buttonFinish2.setVisibility(View.VISIBLE);
        buttonDns2.setVisibility(View.VISIBLE);
        buttonDnf2.setVisibility(View.VISIBLE);
        buttonDisq2.setVisibility(View.VISIBLE);
    }



    public void nextRace(){
        iRace += 1;
        Toast.makeText(getApplicationContext(),"Setting up for race " + iRace.toString(),Toast.LENGTH_LONG).show();

        myChronometer.setVisibility(View.GONE);


        buttonStart.setVisibility(View.VISIBLE);
        buttonStart.setText("Start Race " + iRace.toString());


        buttonFinish1.setVisibility(View.GONE);
        buttonDns1.setVisibility(View.GONE);
        buttonDnf1.setVisibility(View.GONE);
        buttonDisq1.setVisibility(View.GONE);

        buttonFinish2.setVisibility(View.GONE);
        buttonDns2.setVisibility(View.GONE);
        buttonDnf2.setVisibility(View.GONE);
        buttonDisq2.setVisibility(View.GONE);

        buttonFinish1.setEnabled(true);
        buttonDisq1.setEnabled(true);
        buttonDnf1.setEnabled(true);
        buttonDns1.setEnabled(true);

        buttonFinish2.setEnabled(true);
        buttonDisq2.setEnabled(true);
        buttonDnf2.setEnabled(true);
        buttonDns2.setEnabled(true);

        bScoreAwarded = false;

        bWinnerBoat1 = false;
        bWinnerBoat2 = false;

        bEndedBoat1 = false;
        bEndedBoat2 = false;
    }


    public void finishRaceBoat1(View view){
        sResultsBoat1 += myChronometer.getText() + "\n";
        resultsBoat1.setText(sResultsBoat1);
        bEndedBoat1 = true;
        if (!bWinnerBoat2) {
            bWinnerBoat1 = true;
        }
        checkProgress();
    }

    public void finishRaceBoat2(View view){
        sResultsBoat2 += myChronometer.getText() + "\n";
        resultsBoat2.setText(sResultsBoat2);
        bEndedBoat2 = true;
        if (!bWinnerBoat1) {
            bWinnerBoat2 = true;
        }
        checkProgress();
    }

    public void dnsRaceBoat1(View view){
       sResultsBoat1 += this.getString(R.string.dns) + "\n";
       resultsBoat1.setText(sResultsBoat1);
       bEndedBoat1 = true;
       checkProgress();
    }

    public void dnsRaceBoat2(View view){
        sResultsBoat2 += this.getString(R.string.dns) + "\n";
        resultsBoat2.setText(sResultsBoat2);
        bEndedBoat2 = true;
        checkProgress();
    }

    public void dnfRaceBoat1(View view){
        sResultsBoat1 += this.getString(R.string.dnf) + "\n";
        resultsBoat1.setText(sResultsBoat1);
        bEndedBoat1 = true;
        checkProgress();
    }

    public void dnfRaceBoat2(View view){
        sResultsBoat2 += this.getString(R.string.dnf) + "\n";
        resultsBoat2.setText(sResultsBoat2);
        bEndedBoat2 = true;
        checkProgress();
    }

    public void disqRaceBoat1(View view){
        sResultsBoat1 += this.getString(R.string.disq) + "\n";
        resultsBoat1.setText(sResultsBoat1);
        bEndedBoat1 = true;
        checkProgress();
    }

    public void disqRaceBoat2(View view){
        sResultsBoat2 += this.getString(R.string.disq) + "\n";
        resultsBoat2.setText(sResultsBoat2);
        bEndedBoat2 = true;
        checkProgress();
    }

    public void checkProgress(){
        Toast.makeText(getApplicationContext(),"Checking progress...",Toast.LENGTH_LONG).show();

        // INCREASE SCORE
        if (bWinnerBoat1 && ! bScoreAwarded) {
            Toast.makeText(getApplicationContext(),"Boat 1 wins!",Toast.LENGTH_LONG).show();
            iScoreBoat1 += 1;
            scoreBoat1.setText(iScoreBoat1.toString());
            bScoreAwarded = true;
        }

        if (bWinnerBoat2 && ! bScoreAwarded) {
            Toast.makeText(getApplicationContext(),"Boat 2 wins!",Toast.LENGTH_LONG).show();
            iScoreBoat2 += 1;
            scoreBoat2.setText(iScoreBoat2.toString());
            bScoreAwarded = true;
        }

        if (bEndedBoat1 && bEndedBoat2){
            Toast.makeText(getApplicationContext(),"Both boats have ended.",Toast.LENGTH_LONG).show();
            myChronometer.stop();
            nextRace();
        }



        // DISABLE BUTTONS WHEN BOAT ENDS
        if (bEndedBoat1) {
            buttonFinish1.setEnabled(false);
            buttonDisq1.setEnabled(false);
            buttonDnf1.setEnabled(false);
            buttonDns1.setEnabled(false);
        }

        if (bEndedBoat2) {
            buttonFinish2.setEnabled(false);
            buttonDisq2.setEnabled(false);
            buttonDnf2.setEnabled(false);
            buttonDns2.setEnabled(false);
        }

    }




    public void resetClock(View view){
        iRace = 1;
        myChronometer.setVisibility(View.GONE);

        buttonStart.setVisibility(View.VISIBLE);
        buttonStart.setText("Start Race " + iRace.toString());

        buttonReset.setVisibility(View.GONE);

        buttonFinish1.setVisibility(View.GONE);
        buttonDns1.setVisibility(View.GONE);
        buttonDnf1.setVisibility(View.GONE);
        buttonDisq1.setVisibility(View.GONE);
        iScoreBoat1 = 0;
        scoreBoat1.setText(iScoreBoat1.toString());
        sResultsBoat1 = "";
        resultsBoat1.setText(sResultsBoat1);

        buttonFinish2.setVisibility(View.GONE);
        buttonDns2.setVisibility(View.GONE);
        buttonDnf2.setVisibility(View.GONE);
        buttonDisq2.setVisibility(View.GONE);
        iScoreBoat2 = 0;
        scoreBoat2.setText(iScoreBoat2.toString());
        sResultsBoat2 = "";
        resultsBoat2.setText(sResultsBoat2);

        myChronometer.setBase(SystemClock.elapsedRealtime());
        Toast.makeText(getApplicationContext(),"The app has been reset",Toast.LENGTH_LONG).show();
    }

}





