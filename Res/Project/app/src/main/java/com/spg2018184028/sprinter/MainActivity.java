package com.spg2018184028.sprinter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    private ImageButton leftButton, rightButton, jumpButton, pauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leftButton = findViewById(R.id.ButtonLeft);
        rightButton = findViewById(R.id.ButtonRight);
        jumpButton = findViewById(R.id.ButtonJump);
        pauseButton = findViewById(R.id.ButtonPause);
    }

    public void onClickLeftButton(View view) {
        Log.d(TAG, "left button clicked!!");
    }

    public void onClickRightButton(View view) {
        Log.d(TAG, "right button clicked!!");
    }

    public void onClickJumpButton(View view) {
        Log.d(TAG, "jump button clicked!!");
    }

    public void onClickPauseButton(View view) {
        Log.d(TAG, "pause button clicked!!");
    }
}