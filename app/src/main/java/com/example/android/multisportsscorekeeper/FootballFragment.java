package com.example.android.multisportsscorekeeper;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import static android.R.attr.onClick;

/**
 * Created by Adel on 3/27/2017.
 */

public class FootballFragment extends Fragment {

    View rootView;
    private Handler myHandler = new Handler();
    Runnable myRunnable;
    ProgressBar progressBar;
    TextView half, timer, teamARslt, teamBRslt, redCardCounterA, redCardCounterB, yellowCardCounterA, yellowCardCounterB;
    ImageView teamAGoal, teamBGoal, teamARedCard, teamBRedCard, teamAYellowCard, teamBYellowCard;
    Button pause, start, reset;
    private long myMin = 0, mySec = 0;
    private int myTime = 0;
    private int rsltA, rsltB, redA, redB, yellowA, yellowB;
    /*public static FootballFragment newInstance() {
        FootballFragment fragment = new FootballFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.football, container, false);

        progressBar = (ProgressBar)rootView.findViewById(R.id.progressBar);
        half = (TextView) rootView.findViewById(R.id.half);
        timer = (TextView) rootView.findViewById(R.id.time);
        teamAGoal = (ImageView) rootView.findViewById(R.id.teamAGoal);
        teamBGoal = (ImageView) rootView.findViewById(R.id.teamBGoal);
        teamARedCard = (ImageView) rootView.findViewById(R.id.teamARedCard);
        teamBRedCard = (ImageView) rootView.findViewById(R.id.teamBRedCard);
        teamAYellowCard = (ImageView) rootView.findViewById(R.id.teamAYellowCard);
        teamBYellowCard = (ImageView) rootView.findViewById(R.id.teamBYellowCard);
        teamARslt = (TextView) rootView.findViewById(R.id.teamARslt);
        teamBRslt = (TextView) rootView.findViewById(R.id.teamBRslt);
        redCardCounterA = (TextView) rootView.findViewById(R.id.redCardCounterA);
        redCardCounterB = (TextView) rootView.findViewById(R.id.redCardCounterB);
        yellowCardCounterA = (TextView) rootView.findViewById(R.id.yellowCardCounterA);
        yellowCardCounterB = (TextView) rootView.findViewById(R.id.yellowCardCounterB);
        pause = (Button) rootView.findViewById(R.id.pauseBtn);
        start = (Button) rootView.findViewById(R.id.startBtn);
        reset = (Button) rootView.findViewById(R.id.resetBtn);

        timer.setText(R.string.startTime);
        pause.setEnabled(false);
        teamAGoal.setEnabled(false);
        teamBGoal.setEnabled(false);
        teamARedCard.setEnabled(false);
        teamBRedCard.setEnabled(false);
        teamAYellowCard.setEnabled(false);
        teamBYellowCard.setEnabled(false);

        myRunnable = new Runnable() {
            @Override
            public void run() {
                myTime += 1;
                mySec += 1;
                if (mySec == 60){
                    myMin += 1;
                    mySec = 0;
                }
                timer.setText(myMin + ":" + mySec);
                progressBar.setMax(5400);
                progressBar.setProgress(myTime);
                if (myTime == 2700){
                    start.setEnabled(true);
                    pause.setEnabled(false);
                    start.setText(R.string.cont);
                    half.setText(R.string.half);
                    timer.setText(R.string.time);
                    myHandler.removeCallbacks(myRunnable);
                }else if (myTime == 5400){
                    myHandler.removeCallbacks(myRunnable);
                    start.setEnabled(false);
                    pause.setEnabled(false);
                    half.setText(R.string.full);
                    timer.setText(R.string.time);

                }else {
                    myHandler.postDelayed(this, 1000);
                }
            }
        };

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamAGoal.setEnabled(true);
                teamBGoal.setEnabled(true);
                teamARedCard.setEnabled(true);
                teamBRedCard.setEnabled(true);
                teamAYellowCard.setEnabled(true);
                teamBYellowCard.setEnabled(true);
                if (start.getText().equals("START")){
                    start.setEnabled(false);
                    pause.setEnabled(true);
                    myHandler.postDelayed(myRunnable, 1000);
                }else {
                    start.setEnabled(false);
                    pause.setEnabled(true);
                    half.setText(R.string.secondHalf);
                    timer.setText(myMin + ":" + mySec);
                    myHandler.postDelayed(myRunnable, 1000);
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pause.getText().equals("PAUSE")){
                    pause.setText(R.string.cont);
                    teamAGoal.setEnabled(false);
                    teamBGoal.setEnabled(false);
                    myHandler.removeCallbacks(myRunnable);
                }else {
                    pause.setText(R.string.pause);
                    teamAGoal.setEnabled(true);
                    teamBGoal.setEnabled(true);
                    myHandler.postDelayed(myRunnable, 1000);
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHandler.removeCallbacks(myRunnable);
                start.setEnabled(true);
                pause.setEnabled(false);
                start.setText(R.string.start);
                pause.setText(R.string.pause);
                half.setText(R.string.firstHalf);
                timer.setText(R.string.startTime);
                progressBar.setProgress(0);
                myMin = 0;
                mySec = 0;
                myTime = 0;
                rsltA = 0;
                teamARslt.setText("0");
                rsltB = 0;
                teamBRslt.setText("0");
                redA = 0;
                redCardCounterA.setText("0");
                redB = 0;
                redCardCounterB.setText("0");
                yellowA = 0;
                yellowCardCounterA.setText("0");
                yellowB = 0;
                yellowCardCounterB.setText("0");
                teamAGoal.setEnabled(false);
                teamBGoal.setEnabled(false);
                teamARedCard.setEnabled(false);
                teamBRedCard.setEnabled(false);
                teamAYellowCard.setEnabled(false);
                teamBYellowCard.setEnabled(false);

            }
        });

        teamAGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rsltA += 1;
                teamARslt.setText(String.valueOf(rsltA));
            }
        });

        teamBGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rsltB += 1;
                teamBRslt.setText(String.valueOf(rsltB));
            }
        });

        teamARedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redA += 1;
                redCardCounterA.setText(String.valueOf(redA));
            }
        });

        teamBRedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redB += 1;
                redCardCounterB.setText(String.valueOf(redB));
            }
        });

        teamAYellowCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yellowA += 1;
                yellowCardCounterA.setText(String.valueOf(yellowA));
            }
        });

        teamBYellowCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yellowB += 1;
                yellowCardCounterB.setText(String.valueOf(yellowB));
            }
        });

        return rootView;
    }

    /*@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RelativeLayout relativeLayout = (RelativeLayout)rootView.findViewById(R.id.footLayout);
        TextView team1Rslt = new TextView(rootView.getContext());
        TextView team2Rslt = new TextView(rootView.getContext());
        ImageView team1Img = (ImageView)rootView.findViewById(R.id.syrLogo);
        ImageView team2Img = (ImageView)rootView.findViewById(R.id.croLogo);

        team1Rslt.setWidth(30);
        team1Rslt.setHeight(50);
        team1Rslt.setText("0");
        team1Rslt.setX(team1Img.getX() + (team1Img.getWidth()/2) + (team1Rslt.getWidth()/2));
        team1Rslt.setY(team1Img.getY() + team1Img.getHeight() + 20);


        relativeLayout.addView(team1Rslt);
    }*/
}