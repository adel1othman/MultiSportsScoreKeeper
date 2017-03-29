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

/**
 * Created by Adel on 3/27/2017.
 */

public class FootballFragment extends Fragment {

    View rootView;
    private Handler myHandler = new Handler();
    Runnable myRunnable;
    ProgressBar progressBar;
    TextView half, timer, teamARslt, teamBRslt;
    ImageView teamAGoal, teamBGoal;
    Button pause, start, reset;
    private long myMin = 0, mySec = 0;
    private int myTime = 0;
    private int rsltA, rsltB;
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
        teamARslt = (TextView) rootView.findViewById(R.id.teamARslt);
        teamBRslt = (TextView) rootView.findViewById(R.id.teamBRslt);
        pause = (Button) rootView.findViewById(R.id.pauseBtn);
        start = (Button) rootView.findViewById(R.id.startBtn);
        reset = (Button) rootView.findViewById(R.id.resetBtn);

        timer.setText(R.string.startTime);
        pause.setEnabled(false);
        teamAGoal.setEnabled(false);
        teamBGoal.setEnabled(false);

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
                    start.setText("CONTINUE");
                    half.setText("HALF");
                    timer.setText("TIME");
                    myHandler.removeCallbacks(myRunnable);
                }else if (myTime == 5400){
                    myHandler.removeCallbacks(myRunnable);
                    start.setEnabled(false);
                    pause.setEnabled(false);
                    half.setText("FULL");
                    timer.setText("TIME");

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
                if (start.getText().equals("START")){
                    start.setEnabled(false);
                    pause.setEnabled(true);
                    myHandler.postDelayed(myRunnable, 1000);
                }else {
                    start.setEnabled(false);
                    pause.setEnabled(true);
                    half.setText("2ND Half");
                    timer.setText(myMin + ":" + mySec);
                    myHandler.postDelayed(myRunnable, 1000);
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pause.getText().equals("PAUSE")){
                    pause.setText("CONTINUE");
                    myHandler.removeCallbacks(myRunnable);
                }else {
                    pause.setText("PAUSE");
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
                start.setText("START");
                pause.setText("PAUSE");
                half.setText("1ST Half");
                timer.setText(R.string.startTime);
                progressBar.setProgress(0);
                myMin = 0;
                mySec = 0;
                myTime = 0;
                rsltA = 0;
                teamARslt.setText("0");
                rsltB = 0;
                teamBRslt.setText("0");
                teamAGoal.setEnabled(false);
                teamBGoal.setEnabled(false);
            }
        });

        teamAGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rsltA += 1;
                teamARslt.setText("" + rsltA);
            }
        });

        teamBGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rsltB += 1;
                teamBRslt.setText("" + rsltB);
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