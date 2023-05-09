package com.spg2018184028.sprinter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.spg2018184028.sprinter.framework.BaseScene;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private GameView gameView;
    private ImageButton leftButton, rightButton, jumpButton, pauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        new MainScene().pushScene();

        setContentView(R.layout.activity_main);

        leftButton = findViewById(R.id.ButtonLeft);
        rightButton = findViewById(R.id.ButtonRight);
        jumpButton = findViewById(R.id.ButtonJump);
        pauseButton = findViewById(R.id.ButtonPause);
    }

    public void onClickLeftButton(View view)
    {
        MainScene.player.ChangeMoveDir(-1);
    }

    public void onClickRightButton(View view)
    {
        MainScene.player.ChangeMoveDir(1);
    }

    public void onClickJumpButton(View view)
    {
        MainScene.player.Jump();
    }

    public void onClickPauseButton(View view) {
        Log.d(TAG, "pause button clicked!!");
    }

    protected void onPause() {
        gameView.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
    }

    @Override
    protected void onDestroy() {
        BaseScene.popAll();
        super.onDestroy();
    }
}