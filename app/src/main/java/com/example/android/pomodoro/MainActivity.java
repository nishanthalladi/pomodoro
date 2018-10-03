package com.example.android.pomodoro;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private CountDownTimer mCountDownTimer, mBreakTimer;
    private int mBreakCount = 0;
    private TextView timerView, mWorkBreakView;
    private Button mButton;
    double workMinutes = 0.1, breakMinutes = 0.1;
    int cycles = 4;
    long workMillisUntilFinished = (long) (workMinutes*60*1000), breakMillisUntilFinished = (long) (breakMinutes*60*1000);
    DecimalFormat format = new DecimalFormat("##,00");
    private CountDownTimer mLongBreakTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerView = findViewById(R.id.countdown_view);
        mWorkBreakView = findViewById(R.id.work_break_view);

        mBreakTimer = new CountDownTimer(breakMillisUntilFinished, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                String timeString = (millisUntilFinished/(1000*60))+":"+(format.format((millisUntilFinished/1000)%60));
                mWorkBreakView.setText("Take a break!");
                timerView.setText(timeString);
            }
            @Override
            public void onFinish() {}
        };

        mLongBreakTimer = new CountDownTimer(breakMillisUntilFinished*4, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                String timeString = (millisUntilFinished/(1000*60))+":"+(format.format((millisUntilFinished/1000)%60));
                mWorkBreakView.setText("Take a break!");
                timerView.setText(timeString);
            }
            @Override
            public void onFinish() {}
        };

        mCountDownTimer = new CountDownTimer(workMillisUntilFinished, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                String timeString = (millisUntilFinished/(1000*60))+":"+(format.format((millisUntilFinished/1000)%60));
                mWorkBreakView.setText("Working hard?...");
                timerView.setText(timeString);
            }
            @Override
            public void onFinish() {
                if (mBreakCount % 4 == 0)
                    mLongBreakTimer.start();
                else
                    mBreakTimer.start();
            }
        };

        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownTimer.start();
                mBreakCount++;
            }
        });
    }


    private void takeBreak(long breakMillisUntilFinished) {
    }
}
