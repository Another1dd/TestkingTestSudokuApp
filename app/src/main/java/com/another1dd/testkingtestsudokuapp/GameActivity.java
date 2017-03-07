package com.another1dd.testkingtestsudokuapp;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.another1dd.testkingtestsudokuapp.gameEngine.GameEngine;
import com.another1dd.testkingtestsudokuapp.gameEngine.TinyDB;
import com.another1dd.testkingtestsudokuapp.timer.TimeFormat;
import com.another1dd.testkingtestsudokuapp.timer.Timer;

public class GameActivity extends AppCompatActivity {
    TextView mTimerView;
    Handler mTimeHandler;
    public static GameTimer mTimer;
    public static String timeForGameGrid = "";
    TimeFormat mTimeFormat = new TimeFormat();
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_game);


        mTimerView = (TextView) findViewById(R.id.timer);

        mTimer = new GameTimer();
        mTimeHandler = new Handler();

        tinyDB = new TinyDB(this);
        if (tinyDB.getListInt("Sudoku").size() == 0) {
            GameEngine.getInstance().createGrid(this);
        } else {
            mTimer.restoreState(tinyDB);
            GameEngine.getInstance().createGridFromDB(this, tinyDB.getListInt("Sudoku"));
        }

        initializeButtons();


    }

    @Override
    protected void onPause() {
        super.onPause();
        tinyDB = new TinyDB(this);
        mTimer.stop();
        mTimer.saveState(tinyDB);
        tinyDB.putListInt("Sudoku", GameEngine.getInstance().getGridSaveState());


    }

    @Override
    protected void onStop() {
        super.onStop();
        mTimer.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTimer.start();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initializeButtons() {
        Button keypad1 = (Button) findViewById(R.id.keypad_but_1);
        keypad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.getInstance().setNumber(1);
            }
        });

        Button keypad2 = (Button) findViewById(R.id.keypad_but_2);
        keypad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.getInstance().setNumber(2);
            }
        });
        Button keypad3 = (Button) findViewById(R.id.keypad_but_3);
        keypad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.getInstance().setNumber(3);
            }
        });
        Button keypad4 = (Button) findViewById(R.id.keypad_but_4);
        keypad4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.getInstance().setNumber(4);
            }
        });
        Button keypad5 = (Button) findViewById(R.id.keypad_but_5);
        keypad5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.getInstance().setNumber(5);
            }
        });
        Button keypad6 = (Button) findViewById(R.id.keypad_but_6);
        keypad6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.getInstance().setNumber(6);
            }
        });
        Button keypad7 = (Button) findViewById(R.id.keypad_but_7);
        keypad7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.getInstance().setNumber(7);
            }
        });
        Button keypad8 = (Button) findViewById(R.id.keypad_but_8);
        keypad8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.getInstance().setNumber(8);
            }
        });
        final Button keypad9 = (Button) findViewById(R.id.keypad_but_9);
        keypad9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.getInstance().setNumber(9);
                keypad9.animate();
            }
        });
        Button del = (Button) findViewById(R.id.delete_button);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GameEngine.getInstance().setNumber(0);


            }
        });
    }

    void updateTime() {
        timeForGameGrid = mTimeFormat.format(mTimer.getTime());
        mTimerView.setText(timeForGameGrid);

    }

    public static void timerStop() {
        mTimer.stop();
    }


    private final class GameTimer extends Timer {

        GameTimer() {
            super(1000);
        }

        @Override
        protected boolean step(int count, long time) {
            updateTime();

            // Run until explicitly stopped.
            return false;
        }

    }


}
